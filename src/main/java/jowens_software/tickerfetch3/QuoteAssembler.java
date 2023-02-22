package jowens_software.tickerfetch3;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

//@Component
//public class QuoteAssembler implements RepresentationModelAssembler<Quote, EntityModel<Quote>>{
//    @Override
//    publlic EntityModel<SingleQuote>
//
//    @Override
//    public org.springframework.hateoas.EntityModel<Quote> toModel(Quote Quote) {
//        return EntityModel.of(Quote,
//                linkTo(methodOn(QuoteController.class).one(Quote.getId())).withSelfRel(),
//                linkTo(methodOn(QuoteController.class).all()).withRel("employees"));
//    }
//}
