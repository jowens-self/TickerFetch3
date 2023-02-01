package jowens_software.tickerfetch3;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.timeseries.response.QuoteResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


//import com.crazzyghost.alphavantage.AlphaVantage;
//import com.crazzyghost.alphavantage.AlphaVantageException;
//import com.crazzyghost.alphavantage.timeseries.response.QuoteResponse;
//import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {



    @GetMapping("/")
    public String index() {
        return "Select ticker with URL until FE is linked.";
    }

    @GetMapping("/quote/{ticker}")
    public void SingleQuote(@PathVariable String ticker) {
        System.out.println("Fetched Quote for " + ticker);

        AlphaVantage.api().timeSeries().quote()
                .forSymbol(ticker)
                .onSuccess(e -> handleSuccess((QuoteResponse) e))
                .onFailure(TickerFetch3Application::handleFailure)
                .fetch();
    }

//    @GetMapping("/example")
//    public void getExampleQuote(){
//        AlphaVantage.api().timeSeries().quote()
//                .forSymbol("SPY")
//                .onSuccess(e -> Success((QuoteResponse) e))
//                .onFailure(TickerFetch3Application::handleFailure)
//                .fetch();
//    }

    public static void handleSuccess(QuoteResponse response) {
        //plotGraph(response.getStockUnits());

        System.out.println(response.toString());
        SingleQuote q = new SingleQuote(response.getSymbol(), response.getOpen(),
                response.getHigh(), response.getLow(), response.getPrice(),
                response.getVolume(), response.getLatestTradingDay(),
                response.getPreviousClose(), response.getChange(),
                response.getChangePercent());

        //plotGraph(response.getStockUnits());
        System.out.println(q.toString());
    }

    public static void handleFailure(AlphaVantageException error) {
        System.out.println("Error message is: " + error.getMessage());
    }
}
