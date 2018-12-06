package org.schematch_team.parsing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class FriendPairsServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Integer vkUserId = Integer.valueOf(req.getQueryString());
        List<FriendPair> friendPairs = Lists.newArrayList();
        ConnectionJDBC connectionJDBC = null;
       // try {
           // connectionJDBC = new ConnectionJDBC();
            friendPairs = Lists.newArrayList(new FriendPair(1,2),
                    new FriendPair(2,3),
                    new FriendPair(1,3));//connectionJDBC.getFriendPairs(vkUserId);
       // } catch (ClassNotFoundException | SQLException e) {
       //     e.printStackTrace();
       // } finally {
       //     try {
       //         connectionJDBC.close();
       //     } catch (SQLException e) {
       //         e.printStackTrace();
       //     }
       // }

        Set<Integer> ids = Sets.newHashSet();
        for (FriendPair friendPair : friendPairs){
            ids.add(friendPair.getId1());
            ids.add(friendPair.getId2());
        }

        FriendPairResponse friendPairResponse = new FriendPairResponse();
        friendPairResponse.setIds(ids);
        friendPairResponse.setRelations(friendPairs);

        String response = objectMapper.writer().writeValueAsString(friendPairResponse);
        out.println(response);
        out.close();
    }

}
