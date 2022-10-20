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
        AlphaVantage.api().timeSeries().intraday()
                .forSymbol("SPY")
                .onSuccess(e -> handleSuccess((TimeSeriesResponse) e))
                .onFailure(TickerFetch3Application::handleFailure)
                .fetch();

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
        Quote q = new Quote();
        //plotGraph(response.getStockUnits());
        System.out.println(QuoteResponse.QuoteParser.toJSON();
    }

    public static void handleSuccess(TimeSeriesResponse response) {
        //plotGraph(response.getStockUnits());

        System.out.println(response.getStockUnits().);
    }

    public static void handleFailure(AlphaVantageException error) {
        /* uh-oh! */
    }


    public static class Quote {
        private String symbol;
        private double open;
        private double high;
        private double low;
        private double price;
        private double volume;
        private String latestTradingDay;
        private double previousClose;
        private double change;
        private double changePercent;

        public Quote(String symbol, double open, double high, double low, double price, double volume, String latestTradingDay, double previousClose, double change, double changePercent) {
            this.symbol = symbol;
            this.open = open;
            this.high = high;
            this.low = low;
            this.price = price;
            this.volume = volume;
            this.latestTradingDay = latestTradingDay;
            this.previousClose = previousClose;
            this.change = change;
            this.changePercent = changePercent;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public double getOpen() {
            return open;
        }

        public void setOpen(double open) {
            this.open = open;
        }

        public double getHigh() {
            return high;
        }

        public void setHigh(double high) {
            this.high = high;
        }

        public double getLow() {
            return low;
        }

        public void setLow(double low) {
            this.low = low;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getVolume() {
            return volume;
        }

        public void setVolume(double volume) {
            this.volume = volume;
        }

        public String getLatestTradingDay() {
            return latestTradingDay;
        }

        public void setLatestTradingDay(String latestTradingDay) {
            this.latestTradingDay = latestTradingDay;
        }

        public double getPreviousClose() {
            return previousClose;
        }

        public void setPreviousClose(double previousClose) {
            this.previousClose = previousClose;
        }

        public double getChange() {
            return change;
        }

        public void setChange(double change) {
            this.change = change;
        }

        public double getChangePercent() {
            return changePercent;
        }

        public void setChangePercent(double changePercent) {
            this.changePercent = changePercent;
        }
    }
}
