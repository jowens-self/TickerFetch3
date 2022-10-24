package jowens_software.tickerfetch3;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;

import com.crazzyghost.alphavantage.timeseries.response.QuoteResponse;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import jowens_software.tickerfetch3.CurrentQuote;
@SpringBootApplication
public class TickerFetch3Application {

    public static void main(String[] args) {
        SpringApplication.run(TickerFetch3Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializeSession() {
        System.out.println("Session Started.\n\n\n");

        Config cfg = Config.builder()
                .key("EP9UY0QAK3UB8DLX")
                .timeOut(10)
                .build();

        AlphaVantage.api().init(cfg);

        //Not seeing why this one doesn't work and intraday does with
        //the object being returned to timeSeries
//        AlphaVantage.api().timeSeries().quote()
//                .forSymbol("SPY")
//                .onSuccess(e -> handleSuccess((TimeSeriesResponse) e))
//                .onFailure(TickerFetch3Application::handleFailure)
//                .fetch();
//
//        AlphaVantage.api().timeSeries().intraday()
//                .forSymbol("SPY")
//                .onSuccess(e -> handleSuccess((TimeSeriesResponse) e))
//                .onFailure(TickerFetch3Application::handleFailure)
//                .fetch();

        /* cannot call TimeSeriesResponse as does not derive from
        the TimeSeries class, call quote version instead */
        AlphaVantage.api().timeSeries().quote()
                .forSymbol("SPY")
                .onSuccess(e -> handleSuccess((QuoteResponse) e))
                .onFailure(TickerFetch3Application::handleFailure)
                .fetch();
    }

    //public static void handleSuccess(QuoteResponse response) {
    public void handleSuccess(QuoteResponse response) {
        CurrentQuote q = new CurrentQuote(response.getSymbol(), response.getOpen(),
                response.getHigh(), response.getLow(), response.getPrice(),
                response.getVolume(), response.getLatestTradingDay(),
                response.getPreviousClose(), response.getChange(),
                response.getChangePercent());

        //plotGraph(response.getStockUnits());
        System.out.println(q.toString());
    }

    public static void handleSuccess(TimeSeriesResponse response) {
        //plotGraph(response.getStockUnits());

        System.out.println(response.getStockUnits());
    }

    public static void handleFailure(AlphaVantageException error) {
        /* uh-oh! */
    }
}
