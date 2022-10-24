package jowens_software.tickerfetch3;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class SingleQuoteController {
    private final SingleTickerFetchRepository repository;

    SingleQuoteController(SingleTickerFetchRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/quote/{ticker}")
    SingleQuote SingleQuote(@PathVariable String ticker) {

        return repository.findById(ticker)
                .orElseThrow(() -> new SingleTickerQuoteNotFoundException(ticker));
    }

}
