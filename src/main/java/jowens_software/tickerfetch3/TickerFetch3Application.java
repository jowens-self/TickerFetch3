package jowens_software.tickerfetch3;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;

import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class TickerFetch3Application {

    public static void main(String[] args) {
        SpringApplication.run(TickerFetch3Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializeSession() {
        System.out.println("Session Started.\n\n\n");
        //Might want to have this initialized here
        Config cfg = Config.builder()
                .key("EP9UY0QAK3UB8DLX")
                .timeOut(10)
                .build();

        AlphaVantage.api().init(cfg);

        /* "Global Quote" Call by Ticker (Single Quote Return)
        --- Do not call TimeSeriesResponse as does not derive from
        the TimeSeries class, call QuoteResponse instead */


        //"intraday" generic call by ticker
//        AlphaVantage.api().timeSeries().intraday()
//                .forSymbol("SPY")
//                .onSuccess(e -> handleSuccess((TimeSeriesResponse) e))
//                .onFailure(TickerFetch3Application::handleFailure)
//                .fetch();
    }

    //public static void handleSuccess(QuoteResponse response) {


    public static void handleSuccess(TimeSeriesResponse response) {
        //plotGraph(response.getStockUnits());

        System.out.println(response.getStockUnits());
    }

    public static void handleFailure(AlphaVantageException error) {
        /* uh-oh! */
    }
}
