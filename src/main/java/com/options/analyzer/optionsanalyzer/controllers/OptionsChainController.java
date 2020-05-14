package com.options.analyzer.optionsanalyzer.controllers;

import com.options.analyzer.optionsanalyzer.model.Symbols;
import com.options.analyzer.optionsanalyzer.model.entity.OptionsChain;
import com.options.analyzer.optionsanalyzer.repo.OptionsChainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Controller
public class OptionsChainController {

    private final OptionsChainRepository optionsChainRepository;

    @Autowired
    public OptionsChainController(OptionsChainRepository optionsChainRepository) {
        this.optionsChainRepository = optionsChainRepository;
    }

    @PostMapping("/getOptionsChain")
    public ModelAndView getOptionsChain(@ModelAttribute Symbols symbols, ModelMap model) {
        List<OptionsChain> chains = optionsChainRepository.findBySymbol(symbols.getSymbolId());
        Map<LocalDate, Map<String, List<OptionsChain>>> chainMap = chains.stream().collect(
                groupingBy(OptionsChain::getExpirationDate, groupingBy(OptionsChain::getSide)));
        model.addAttribute("chainMap", chainMap);
        return new ModelAndView("index", model);
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("symbols", new Symbols());
        return "index";
    }
}
