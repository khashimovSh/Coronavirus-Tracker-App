package io.shakhzod.coronavirus.controllers;

import io.shakhzod.coronavirus.models.LocationStats;
import io.shakhzod.coronavirus.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CoronaController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;


    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats",coronaVirusDataService.getAllStats());
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);
        return "home";
    }
    @PostMapping("/search")
    public String coronaPostSearch(@RequestParam String country, Model model)
    {
        List<LocationStats> exactCountries = coronaVirusDataService.searchByCountry(country);
        if(exactCountries.size()!=0) {
            int totalReportedCases = exactCountries.stream().mapToInt(LocationStats::getLatestTotalCases).sum();
            int totalNewCases = exactCountries.stream().mapToInt(LocationStats::getDiffFromPrevDay).sum();
            model.addAttribute("country", country);
            model.addAttribute("locationStats", coronaVirusDataService.searchByCountry(country));
            model.addAttribute("totalReportedCases", totalReportedCases);
            model.addAttribute("totalNewCases", totalNewCases);
        }
        else
        {
            model.addAttribute("country", "Please enter country with proper name\n\nfor example: Poland");
        }
        return "searchResult";
    }

}
