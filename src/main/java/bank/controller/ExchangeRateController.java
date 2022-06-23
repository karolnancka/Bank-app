package bank.controller;

import bank.model.ExchangeRate;
import bank.repository.ExchangeRateRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/exchangeRate")
public class ExchangeRateController {

    private final ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateController(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }


    @GetMapping("/all")
    @Transactional
    public String showAll(Model model){

            //Rates from URL
            ExchangeRate exchangeRate = exchangeRateRepository.getOne(1L);
            //USD to EUR
            final String usdToEurURL = "https://www.google.com/search?q=usd+to+eur&oq=usd+to+eur&aqs=chrome..69i57j0i433i512j0i512l8.3480j1j7&sourceid=chrome&ie=UTF-8";
            try {
                final Document document = Jsoup.connect(usdToEurURL).get();
                final String ticker = document.select("span.SwHCTb.DFlfde").text();
                final String rateUsdEur = ticker.replace(",", ".");
                final double usdEur = Double.parseDouble(rateUsdEur);
                System.out.println(usdEur);
                exchangeRate.setUsdToEur(usdEur);
                model.addAttribute("usdEur", usdEur);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //EUR to USD
            final String eurToUsdURL = "https://www.google.com/search?q=EUR+to+USD&oq=EUR+to+USD&aqs=chrome..69i57j0i512l9.3889j1j4&sourceid=chrome&ie=UTF-8";
            try {
                final Document document = Jsoup.connect(eurToUsdURL).get();
                final String ticker = document.select("span.SwHCTb.DFlfde").text();
                final String rateEurUsd = ticker.replace(",", ".");
                final double eurUsd = Double.parseDouble(rateEurUsd);
                System.out.println(eurUsd);
                exchangeRate.setEurToUsd(eurUsd);
                model.addAttribute("eurUsd", eurUsd);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //USD to PLN
            final String usdToPlnURL = "https://www.google.com/search?q=USD+to+PLN&oq=USD+to+PLN&aqs=chrome..69i57j35i39j0i512l8.2081j1j7&sourceid=chrome&ie=UTF-8";
            try {
                final Document document = Jsoup.connect(usdToPlnURL).get();
                final String ticker = document.select("span.SwHCTb.DFlfde").text();
                final String rateUsdPln = ticker.replace(",", ".");
                final double usdPln = Double.parseDouble(rateUsdPln);
                System.out.println(usdPln);
                exchangeRate.setUsdToPln(usdPln);
                model.addAttribute("usdPln", usdPln);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //PLN to USD
            final String plnToUsdURL = "https://www.google.com/search?q=PLN+to+USD&sxsrf=ALiCzsaXbKATpQbrfc3H2VeD-5GyZw81LQ%3A1656015825630&ei=0cu0Yq2BJsXRqwGZ5o6ABA&ved=0ahUKEwjtrZ3Ys8T4AhXF6CoKHRmzA0AQ4dUDCA8&uact=5&oq=PLN+to+USD&gs_lcp=Cgdnd3Mtd2l6EAMyDwgAELEDEIMBEEMQRhCCAjIFCAAQgAQyBQgAEMsBMgUIABDLATIFCAAQgAQyBQgAEMsBMgUIABDLATIFCAAQgAQyBQgAEMsBMgUIABCABDoGCCMQJxATOgQILhAnOgQIIxAnOgQIABBDOgoIABCxAxCDARBDOgsIABCABBCxAxCDAToRCC4QgAQQsQMQgwEQxwEQowI6CAgAEIAEELEDOgcIABCABBAKOgcIABAKEMsBSgQIQRgASgQIRhgAUABY8xFg5RNoAHABeACAAZkBiAGdB5IBAzkuMZgBAKABAcABAQ&sclient=gws-wiz";
            try {
                final Document document = Jsoup.connect(plnToUsdURL).get();
                final String ticker = document.select("span.SwHCTb.DFlfde").text();
                final String ratePlnUsd = ticker.replace(",", ".");
                final double plnUsd = Double.parseDouble(ratePlnUsd);
                System.out.println(plnUsd);
                exchangeRate.setPlnToUsd(plnUsd);
                model.addAttribute("plnUsd", plnUsd);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //EUR to PLN
            final String eurToPlnURL = "https://www.google.com/search?q=EUR+to+PLN&oq=EUR+to+PLN&aqs=chrome..69i57j0i512l9.3184j1j7&sourceid=chrome&ie=UTF-8";
            try {
                final Document document = Jsoup.connect(eurToPlnURL).get();
                final String ticker = document.select("span.SwHCTb.DFlfde").text();
                final String rateEurPln = ticker.replace(",", ".");
                final double eurPln = Double.parseDouble(rateEurPln);
                System.out.println(eurPln);
                exchangeRate.setEurToPln(eurPln);
                model.addAttribute("eurPln", eurPln);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //PLN to EUR
            final String plnToEurURL = "https://www.google.com/search?q=PLN+to+EUR&sxsrf=ALiCzsbrSeYrlJNvoEXMXqv_yao3Gqfcvw%3A1656016276807&ei=lM20YvPWMIfurgSqzJ6gBw&ved=0ahUKEwjz8K6vtcT4AhUHt4sKHSqmB3QQ4dUDCA8&uact=5&oq=PLN+to+EUR&gs_lcp=Cgdnd3Mtd2l6EAMyCQgjECcQRhCCAjIECCMQJzIFCAAQgAQyBQgAEMsBMgUIABCABDIFCAAQywEyBQgAEIAEMgUIABDLATIFCAAQgAQyBQgAEMsBOgYIIxAnEBM6BAguECc6BAgAEEM6CwgAEIAEELEDEIMBOgoIABCxAxCDARBDOhEILhCABBCxAxCDARDHARCjAjoHCAAQsQMQQzoICAAQgAQQsQNKBAhBGABKBAhGGABQAFjoJWClKmgCcAF4AIABaIgBjQiSAQQxMS4xmAEAoAEBwAEB&sclient=gws-wiz";
            try {
                final Document document = Jsoup.connect(plnToEurURL).get();
                final String ticker = document.select("span.SwHCTb.DFlfde").text();
                final String ratePlnEur = ticker.replace(",", ".");
                final double plnEur = Double.parseDouble(ratePlnEur);
                System.out.println(plnEur);
                exchangeRate.setPlnToEur(plnEur);
                model.addAttribute("plnEur", plnEur);
            } catch (IOException e) {
                e.printStackTrace();
            }

            exchangeRate.setDate(LocalDate.now());




        List<ExchangeRate> rates = exchangeRateRepository.findAll();
        model.addAttribute("rates", rates);
        return "/exchangeRate/allRates";
    }


}
