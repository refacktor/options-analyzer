package com.options.analyzer.optionsanalyzer.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.uuid.Generators;
import com.options.analyzer.optionsanalyzer.model.entity.OptionPair;

public class Utils {

	public static LocalDateTime getLocalDateTime(String quoteDetail, String symbol, String optionType) {
		String date = quoteDetail.substring(quoteDetail.lastIndexOf("/") + 1);
		date = date.substring(0, date.lastIndexOf(":"));
		date = date.substring(symbol.length() + 1, date.length() - optionType.length() - 1);
		String year = date.substring(0, 4);
		String monthSpliter = date.substring(5);
		String month = monthSpliter.split(":")[0];
		String day = monthSpliter.split(":")[1];
		day = day.length() < 2 ? "0" + day : day;
		month = month.length() < 2 ? "0" + month : month;
		return LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day))
				.atStartOfDay(ZoneOffset.UTC).toLocalDateTime();
	}
	
	public static List<OptionPair> getOptionPairs(JsonNode rootNode) {
		List<OptionPair> results = new ArrayList<>();
		rootNode.withArray("options").elements().forEachRemaining(node -> {
			JsonNode optionChainResponseNode = node.get("OptionChainResponse");
			optionChainResponseNode.withArray("OptionPair").elements().forEachRemaining(optionPairNode -> {
				long uniquePair = Generators.timeBasedGenerator().generate().timestamp();
				JsonNode callNode = optionPairNode.get("Call");
				JsonNode putNode = optionPairNode.get("Put");
				results.add(OptionPair.getOptionPair(uniquePair, callNode));
				results.add(OptionPair.getOptionPair(uniquePair, putNode));
			});
		});
		return results;
	}
}
