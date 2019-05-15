package com.findMeNow.validation;

import com.findMeNow.enums.RelationshipStatus;
import com.findMeNow.exception.BadRequestException;
import com.findMeNow.models.Relationship;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;


@Component
public class RelationshipValidation {


    public void validationForUpdateRelationship(Relationship relationshipNow) throws BadRequestException {
        RelationshipStatus relationshipStatus = relationshipNow.getRelationshipStatus();

        RelationshipStatus[] relationshipStatuses =
                {RelationshipStatus.DELETED, RelationshipStatus.CANCELED, RelationshipStatus.REJECTED};

        ArrayList<RelationshipStatus> statuses =
                new ArrayList<>(Arrays.asList(relationshipStatuses));

        if (relationshipStatus.equals(RelationshipStatus.CANCELED) &&
                !relationshipStatus.equals(RelationshipStatus.REQUEST))
            throw new BadRequestException("updating [CANCELED] relationships user: "
                    + relationshipNow.getUserFrom() + " and user: "
                    + relationshipNow.getUserTo() + " are failed");

        if (relationshipStatus.equals(RelationshipStatus.DELETED) &&
                !relationshipStatus.equals(RelationshipStatus.FRIENDS))
            throw new BadRequestException("updating [DELETED] relationships user: "
                    + relationshipNow.getUserFrom() + " and user: "
                    + relationshipNow.getUserTo() + " are failed");

        if (relationshipStatus.equals(RelationshipStatus.FRIENDS) &&
                !relationshipStatus.equals(RelationshipStatus.REQUEST))
            throw new BadRequestException("updating [FRIENDS] relationships user: "
                    + relationshipNow.getUserFrom() + " and user: "
                    + relationshipNow.getUserTo() + " are failed");

        if (relationshipStatus.equals(RelationshipStatus.REJECTED) &&
                !relationshipStatus.equals(RelationshipStatus.REQUEST))
            throw new BadRequestException("updating [REJECTED] relationships user: "
                    + relationshipNow.getUserFrom() + " and user: "
                    + relationshipNow.getUserTo() + " are failed");

        if (relationshipStatus.equals(RelationshipStatus.REQUEST) &&
                !statuses.contains(relationshipStatus))
            throw new BadRequestException("updating [CANCELED] relationships user: "
                    + relationshipNow.getUserFrom() + " and user: "
                    + relationshipNow.getUserTo() + " are failed");
    }


    public void validationForSaveRelationship(Relationship relationship) throws BadRequestException {

        if (relationship != null) {
            if (!relationship.getRelationshipStatus().equals(RelationshipStatus.DELETED)) {
                throw new BadRequestException("user with id: " + relationship.getUserFrom()
                        + "and user with id: " + relationship.getUserTo() + "have a relationship");
            }
        }
    }

}
