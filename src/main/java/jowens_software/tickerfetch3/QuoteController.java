package jowens_software.tickerfetch3;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.timeseries.response.QuoteResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicReference;


@RestController
public class QuoteController {
    private static Quote singleQuote;
    @GetMapping("/")
    public String index() {
        return "Select ticker with URL until FE is linked.";
    }

    @GetMapping("/quote/{ticker}")
    public Quote SingleQuote(@PathVariable String ticker) {

        //AtomicReference<Quote> q = new AtomicReference<>(new Quote());
        Quote q = new Quote();
        AlphaVantage.api().timeSeries().quote()
                .forSymbol(ticker)
                //.onSuccess(e -> q.set(MapQuoteResponse((QuoteResponse) e)))
                .onSuccess(e -> handleSuccess((QuoteResponse) e))
                .onFailure(TickerFetch3Application::handleFailure)
                .fetch();

        q = singleQuote;
        return q;
    }


    @GetMapping("/console/print/quote/{ticker}")
    public void ConsolePrintQuote(@PathVariable String ticker) {
        System.out.println("Fetched Quote for " + ticker);

        AlphaVantage.api().timeSeries().quote()
                .forSymbol(ticker)
                .onSuccess(e -> handleSuccess((QuoteResponse) e))
                .onFailure(TickerFetch3Application::handleFailure)
                .fetch();
    }

    @GetMapping("/example")
    public void getExampleQuote(){
        AlphaVantage.api().timeSeries().quote()
                .forSymbol("SPY")
                .onSuccess(e -> handleSuccess((QuoteResponse) e))
                .onFailure(TickerFetch3Application::handleFailure)
                .fetch();
    }

    public static void handleSuccess(QuoteResponse response) {
        //plotGraph(response.getStockUnits());

        System.out.println(response.toString());
        Quote q = new Quote(response.getSymbol(), response.getOpen(),
                response.getHigh(), response.getLow(), response.getPrice(),
                response.getVolume(), response.getLatestTradingDay(),
                response.getPreviousClose(), response.getChange(),
                response.getChangePercent());

        //plotGraph(response.getStockUnits());
        System.out.println(q.toString());

        singleQuote = q;
            //return q;
    }

    public static Quote MapQuoteResponse(QuoteResponse response) {

        Quote q = new Quote(response.getSymbol(), response.getOpen(),
                response.getHigh(), response.getLow(), response.getPrice(),
                response.getVolume(), response.getLatestTradingDay(),
                response.getPreviousClose(), response.getChange(),
                response.getChangePercent());
        return q;
    }

    public static void handleFailure(AlphaVantageException error) {
        System.out.println("Error message is: " + error.getMessage());
    }


    public String convertResponseToString(QuoteResponse response) {
        Quote q = new Quote(response.getSymbol(), response.getOpen(),
                response.getHigh(), response.getLow(), response.getPrice(),
                response.getVolume(), response.getLatestTradingDay(),
                response.getPreviousClose(), response.getChange(),
                response.getChangePercent());

        return q.toString();
    }
}
