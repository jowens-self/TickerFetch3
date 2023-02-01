package jowens_software.tickerfetch3;

import org.springframework.data.jpa.repository.JpaRepository;

interface QuoteRepository extends JpaRepository<SingleQuote, String> {
}
