package jowens_software.tickerfetch3;


import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.timeseries.response.QuoteResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class SingleQuoteController {

//    @RequestMapping(value = "/error")
//    public String error() {
//        return "Error handling";
//    }


    @GetMapping("/quote/{ticker}")
    void SingleQuote(@PathVariable String ticker) {
        System.out.println("Fetched Quote for " + ticker);

        AlphaVantage.api().timeSeries().quote()
                .forSymbol(ticker)
                .onSuccess(e -> handleSuccess((QuoteResponse) e))
                .onFailure(TickerFetch3Application::handleFailure)
                .fetch();
    }

    public void handleSuccess(QuoteResponse response) {
        SingleQuote q = new SingleQuote(response.getSymbol(), response.getOpen(),
                response.getHigh(), response.getLow(), response.getPrice(),
                response.getVolume(), response.getLatestTradingDay(),
                response.getPreviousClose(), response.getChange(),
                response.getChangePercent());

        //plotGraph(response.getStockUnits());
        System.out.println(q.toString());
    }

    public static void handleFailure(AlphaVantageException error) {
        /* uh-oh! */
    }
}