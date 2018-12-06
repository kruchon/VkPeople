var userId
function getPairs(){
    userId = document.getElementById("user_id").value
    console.log(userId)
    $.get("/getFriendPairs?" + userId, afterPairResponse)
}


function afterPairResponse(response){

    console.log(response)

    response = JSON.parse(response)
    var ids = response.ids

    var relations = response.relations


    var sys = arbor.ParticleSystem(100, 400, 1);
    sys.parameters({gravity: true});
    sys.renderer = Renderer("#viewport");
    var sysNodes = []
    ids.forEach(function (id) {
        sysNodes[id.toString()] = sys.addNode(id.toString(), {
            'color': 'white',
            'shape': 'text',
            'label': id.toString()
        })
    })

    sysNodes[userId.toString()] = sys.addNode(userId.toString(), {'color': 'green', 'shape': 'dot', 'label': "current user"})
    var edges = []
    relations.forEach(function (relation) {
        var firstIndex = relation.id1.toString()
        var secondIndex = relation.id2.toString()
        var edge = sys.addEdge(sysNodes[firstIndex], sysNodes[secondIndex])
        var edge = sys.addEdge(sysNodes[secondIndex], sysNodes[firstIndex])
        if (edges[firstIndex] == null) edges[firstIndex] = []
        edges[firstIndex].push(edge)
        if (edges[secondIndex] == null) edges[secondIndex] = []
        edges[secondIndex].push(edge)
    })
}