package test.infra;
import test.domain.*;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;

@Component
public class MovieHateoasProcessor implements RepresentationModelProcessor<EntityModel<Movie>>  {

    @Override
    public EntityModel<Movie> process(EntityModel<Movie> model) {
        model.add(Link.of(model.getRequiredLink("self").getHref() + "/chkAndReqReserve").withRel("chkAndReqReserve"));

        
        return model;
    }
    
}
