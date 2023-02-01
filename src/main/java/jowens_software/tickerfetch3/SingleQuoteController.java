package jowens_software.tickerfetch3;


import com.crazzyghost.alphavantage.timeseries.response.QuoteResponse;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SingleQuoteController {

//    @RequestMapping(value = "/error")
//    public String error() {
//        return "Error handling";
//    }

//    @GetMapping("/quote/{ticker}")
//    public AtomicReference<String> SingleQuote(@PathVariable String ticker) {
//        System.out.println("Fetched Quote for " + ticker);
//
//        AtomicReference<String> result = new AtomicReference<>("");
//        AlphaVantage.api().timeSeries().quote()
//
//                .forSymbol(ticker)
//                //.onSuccess(e -> handleSuccess((QuoteResponse) e))
//                .onSuccess(e -> {
//                            result.set(fetchResult((QuoteResponse) e));
//                        }
//                )
//                .onFailure(TickerFetch3Application::handleFailure)
//                .fetch();
//        return result;
//    }

    public String convertResponseToString(QuoteResponse response) {
        SingleQuote q = new SingleQuote(response.getSymbol(), response.getOpen(),
                response.getHigh(), response.getLow(), response.getPrice(),
                response.getVolume(), response.getLatestTradingDay(),
                response.getPreviousClose(), response.getChange(),
                response.getChangePercent());
            return q.toString();
    }
}