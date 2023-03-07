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
        Quote q = new Quote();
        AlphaVantage.api().timeSeries().quote()
                .forSymbol(ticker)
                .onSuccess(e -> handleSuccess((QuoteResponse) e))
                .onFailure(TickerFetch3Application::handleFailure)
                .fetch();

//        //https://stackoverflow.com/questions/10850563/how-to-implement-wait-in-a-method-for-another-method-to-be-get-over-in-java

        q = singleQuote;
        return q;
    }

    public static void handleSuccess(QuoteResponse response) {
        Quote q = new Quote(response.getSymbol(), response.getOpen(),
                response.getHigh(), response.getLow(), response.getPrice(),
                response.getVolume(), response.getLatestTradingDay(),
                response.getPreviousClose(), response.getChange(),
                response.getChangePercent());

        //plotGraph(response.getStockUnits());
        System.out.println(q);

        singleQuote = q;

    }

    public static void handleFailure(AlphaVantageException error) {
        System.out.println("Error message is: " + error.getMessage());
    }
}
