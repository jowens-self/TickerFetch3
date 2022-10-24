package jowens_software.tickerfetch3;

import org.springframework.data.jpa.repository.JpaRepository;


interface SingleTickerFetchRepository extends JpaRepository <SingleQuote, String> {
}
