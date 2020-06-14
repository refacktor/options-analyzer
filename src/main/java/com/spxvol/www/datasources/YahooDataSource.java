package com.spxvol.www.datasources;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.spxvol.jsonschema2pojo.yahoo.YahooOptionData;
import com.spxvol.jsonschema2pojo.yahoo.YahooResult;
import com.spxvol.www.datastore.OptionQuote;
import com.spxvol.www.datastore.Underlying;
import com.spxvol.www.model.StandardQuote;

@Controller
public class YahooDataSource implements OptionsDataSource {

	private final Logger logger = Logger.getLogger(getClass().getName());
	
	private static final String url = "https://query2.finance.yahoo.com/v7/finance/options/{symbol}";

	private RestTemplate rt = new RestTemplate();

	@Override
	@GetMapping("/internal/yahoo-debug/{stock}")
	@ResponseBody
	public StandardQuote getQuote(@PathVariable("stock") String stock) {
		final String yahooSymbol = stock.equals("SPX") ? "^" + stock : stock;
		YahooOptionData frontOption = rt.getForObject(url, YahooOptionData.class, yahooSymbol);
		
		final List<Long> expirationDates = frontOption.getOptionChain().getResult().get(0).getExpirationDates();
		Stream<OptionQuote> backOptions = expirationDates.subList(1, expirationDates.size()).stream().flatMap(date -> {
			YahooOptionData backOption = rt.getForObject(url + "?date={date}", YahooOptionData.class, yahooSymbol, date);
			return convert(stock, backOption).getOptions().stream();
		});
		final StandardQuote convert = convert(stock, frontOption);
		backOptions.forEach(convert.getOptions()::add);
		return convert;
	}

	protected StandardQuote convert(String stock, YahooOptionData quote) {
		StandardQuote standardQuote = new StandardQuote();
		final YahooResult result = quote.getOptionChain().getResult().get(0);
		final Underlying underlying = new Underlying(stock);
		underlying.setPrice(result.getQuote().getRegularMarketPrice());
		underlying.setLastTrade(Instant.ofEpochSecond(result.getQuote().getRegularMarketTime()));
		standardQuote.setUnderlying(underlying);
		
		List<OptionQuote> options = result.getOptions().stream().flatMap(in -> {
			final LocalDate expirationDate = Instant.ofEpochSecond(in.getExpirationDate()).atZone(ZoneId.of("UTC")).toLocalDate();
			Stream<OptionQuote> callStream = in.getCalls().stream().map(o -> {
				OptionQuote out = new OptionQuote();
				out.setOptionType("CALL");
				out.setExpiration(expirationDate);
				out.setStrikePrice(o.getStrike());
				out.setLastPrice(o.getLastPrice());
				out.setVolume(o.getVolume() != null ? o.getVolume() : 0);
				out.setInTheMoney(o.getInTheMoney() ? "y": "n");
				out.setIv(o.getImpliedVolatility());
				out.setOpenInterest((int) (o.getOpenInterest() != null ? o.getOpenInterest() : 0));
				out.setBid(o.getBid());
				out.setAsk(o.getAsk());
				out.setDisplaySymbol(o.getContractSymbol());
				out.setSymbol(stock);
				return out;
			});
			Stream<OptionQuote> putStream = in.getPuts().stream().map(o -> {
				OptionQuote out = new OptionQuote();
				out.setOptionType("PUT");
				out.setExpiration(expirationDate);
				out.setStrikePrice(o.getStrike());
				out.setLastPrice(o.getLastPrice());
				out.setVolume(o.getVolume() != null ? o.getVolume() : 0);
				out.setInTheMoney(o.getInTheMoney() ? "y": "n");
				out.setIv(o.getImpliedVolatility());
				out.setOpenInterest((int) (o.getOpenInterest() != null ? o.getOpenInterest() : 0));
				out.setBid(o.getBid());
				out.setAsk(o.getAsk());
				out.setDisplaySymbol(o.getContractSymbol());
				out.setSymbol(stock);
				return out;
			});
			return Stream.of(callStream,putStream).flatMap(stream->stream);
		}).collect(Collectors.toList());
		standardQuote.setOptions(options);
		return standardQuote;
	}
	
}
