package jowens_software.tickerfetch3;

public class SingleTickerQuoteNotFoundException extends RuntimeException{
    SingleTickerQuoteNotFoundException(String ticker) {
        super ("Could not find ticker: " + ticker);
    }
}
