package org.schematch_team.parsing;

import java.util.Collection;

public class FriendPairResponse {

    private Collection<Integer> ids;

    private Collection<FriendPair> relations;

    public Collection<FriendPair> getRelations() {
        return relations;
    }

    public void setRelations(Collection<FriendPair> relations) {
        this.relations = relations;
    }

    public Collection<Integer> getIds() {
        return ids;
    }

    public void setIds(Collection<Integer> ids) {
        this.ids = ids;
    }
}
