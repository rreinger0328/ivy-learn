package com.ivy.vueflow.builder;


import com.alibaba.fastjson.JSON;
import com.ivy.vueflow.parser.entity.FlowData;
import com.ivy.vueflow.parser.execption.LiteFlowELException;
import com.ivy.vueflow.parser.graph.Graph;
import com.ivy.vueflow.parser.graph.GraphInfo;

public class BuilderTest {

    public static void main(String[] args) throws LiteFlowELException {
//        String jsonData="{\"nodes\":[{\"id\":\"832ecc6e537744398e778310f4be7a8e\",\"type\":\"switch\",\"initialized\":false,\"position\":{\"x\":75.41731051076596,\"y\":70.96128555931324},\"data\":{\"id\":\"cdcd069e3b38454db6f18993812ac180\",\"type\":\"switch\",\"name\":\"选择组件\",\"mode\":\"default\",\"nodeDataBase\":{\"classType\":1,\"retryExceptions\":[]},\"style\":{\"toolbar\":{\"position\":\"top\",\"showIcon\":false,\"offset\":10},\"handles\":[{\"position\":\"left\",\"opacity\":1,\"type\":\"target\",\"style\":\"\",\"id\":\"e744cd1e4852484aa17d760ef59ee2ed\"},{\"position\":\"right\",\"opacity\":1,\"type\":\"source\",\"style\":\"\",\"id\":\"a8297a3ed14545f29dca3297aff5183a\"},{\"position\":\"top\",\"opacity\":0,\"type\":\"target\",\"style\":\"\",\"id\":\"4233b41b50cf4ddc9c887d4e91eb3717\"},{\"position\":\"bottom\",\"opacity\":0,\"type\":\"source\",\"style\":\"\",\"id\":\"841a84cf779f439e9bf4b7e0d1471557\"}],\"extendHandles\":[]},\"nodeDataSwitch\":{}},\"label\":\"选择组件\"},{\"id\":\"87d09e585acb48b990d885fc2d6ff17f\",\"type\":\"common\",\"initialized\":false,\"position\":{\"x\":114.41731051076596,\"y\":238.96128555931324},\"data\":{\"id\":\"d784d716a3524862b83dd5ea3c7d2907\",\"type\":\"common\",\"name\":\"普通组件\",\"mode\":\"default\",\"nodeDataBase\":{\"classType\":1,\"retryExceptions\":[]},\"style\":{\"toolbar\":{\"position\":\"top\",\"showIcon\":false,\"offset\":10},\"handles\":[{\"position\":\"left\",\"opacity\":1,\"type\":\"target\",\"style\":\"\",\"id\":\"486697294aae44cf883cdf29d65eec3d\"},{\"position\":\"right\",\"opacity\":1,\"type\":\"source\",\"style\":\"\",\"id\":\"86fb5dfd546c4685a67280cbd8eccec4\"},{\"position\":\"top\",\"opacity\":0,\"type\":\"target\",\"style\":\"\",\"id\":\"887323dec374482c97710e2ff2628a40\"},{\"position\":\"bottom\",\"opacity\":0,\"type\":\"source\",\"style\":\"\",\"id\":\"8972608497824a70a5fd7d92825974c7\"}],\"extendHandles\":[]}},\"label\":\"普通组件\"}],\"edges\":[{\"id\":\"cf70ae0c17414928a01405b6d50657e8\",\"type\":\"animation\",\"source\":\"832ecc6e537744398e778310f4be7a8e\",\"target\":\"87d09e585acb48b990d885fc2d6ff17f\",\"sourceHandle\":\"e744cd1e4852484aa17d760ef59ee2ed\",\"targetHandle\":\"486697294aae44cf883cdf29d65eec3d\",\"updatable\":true,\"data\":{\"type\":\"common\"},\"label\":\"\",\"animated\":true,\"markerEnd\":\"arrowclosed\",\"showToolbar\":false}],\"position\":[64.92643948923403,14.53871444068676],\"zoom\":1,\"viewport\":{\"x\":64.92643948923403,\"y\":14.53871444068676,\"zoom\":1},\"format\":false}" ;
//        String jsonData="{\"nodes\":[{\"id\":\"bb975a19-dc07-4649-b584-5b283038941d\",\"type\":\"switch\",\"x\":610,\"y\":290,\"properties\":{\"id\":\"1829396285750931456\",\"type\":\"third\",\"lock\":\"0\",\"release\":\"0\",\"nodeId\":\"test\",\"nodeName\":\"test\",\"subProductName\":\"测试\",\"ui\":\"node-red\"},\"text\":{\"x\":630,\"y\":292,\"value\":\"test\"}},{\"id\":\"d29c4770-b51b-41e0-bfdf-3bd79e55207a\",\"type\":\"third\",\"x\":620,\"y\":380,\"properties\":{\"id\":\"1829397868748369920\",\"type\":\"third\",\"lock\":\"0\",\"release\":\"0\",\"nodeId\":\"test_copy\",\"nodeName\":\"test_copy\",\"subProductName\":\"测试\",\"ui\":\"node-red\"},\"text\":{\"x\":640,\"y\":382,\"value\":\"test_copy\"}},{\"id\":\"d9729aa6-954a-4cda-af6b-383770fc51ec\",\"type\":\"agvListen\",\"x\":610,\"y\":470,\"properties\":{\"id\":\"1829413755072299008\",\"type\":\"agvListen\",\"lock\":\"0\",\"release\":\"0\",\"nodeId\":\"g\",\"nodeName\":\"g\",\"subProductName\":\"测试\",\"ui\":\"node-red\"},\"text\":{\"x\":630,\"y\":472,\"value\":\"g\"}},{\"id\":\"e8b57cb9-ccfc-471f-91a5-73b1a63e2e9b\",\"type\":\"third\",\"x\":620,\"y\":550,\"properties\":{\"id\":\"1838125323892842496\",\"type\":\"third\",\"lock\":\"0\",\"release\":\"1\",\"nodeId\":\"test_copy_copy\",\"nodeName\":\"test_copy_copy\",\"subProductName\":\"测试\",\"ui\":\"node-red\"},\"text\":{\"x\":640,\"y\":552,\"value\":\"test_copy_copy\"}}],\"edges\":[{\"id\":\"49449434-48ed-4cee-92bc-2d07449e12bb\",\"type\":\"flow-link\",\"sourceNodeId\":\"bb975a19-dc07-4649-b584-5b283038941d\",\"targetNodeId\":\"d29c4770-b51b-41e0-bfdf-3bd79e55207a\",\"startPoint\":{\"x\":590,\"y\":310},\"endPoint\":{\"x\":596.6666666666666,\"y\":360},\"properties\":{},\"pointsList\":[{\"x\":590,\"y\":310},{\"x\":590,\"y\":410},{\"x\":596.6666666666666,\"y\":260},{\"x\":596.6666666666666,\"y\":360}]},{\"id\":\"16ab9539-09d7-4c37-9099-6c825db6cf1c\",\"type\":\"flow-link\",\"sourceNodeId\":\"d29c4770-b51b-41e0-bfdf-3bd79e55207a\",\"targetNodeId\":\"d9729aa6-954a-4cda-af6b-383770fc51ec\",\"startPoint\":{\"x\":596.6666666666666,\"y\":400},\"endPoint\":{\"x\":586.6666666666666,\"y\":450},\"properties\":{},\"pointsList\":[{\"x\":596.6666666666666,\"y\":400},{\"x\":596.6666666666666,\"y\":500},{\"x\":586.6666666666666,\"y\":350},{\"x\":586.6666666666666,\"y\":450}]},{\"id\":\"c6cc7fe7-9677-405d-858f-f3169f17b038\",\"type\":\"flow-link\",\"sourceNodeId\":\"d9729aa6-954a-4cda-af6b-383770fc51ec\",\"targetNodeId\":\"e8b57cb9-ccfc-471f-91a5-73b1a63e2e9b\",\"startPoint\":{\"x\":590,\"y\":490},\"endPoint\":{\"x\":590,\"y\":530},\"properties\":{},\"pointsList\":[{\"x\":590,\"y\":490},{\"x\":590,\"y\":590},{\"x\":590,\"y\":430},{\"x\":590,\"y\":530}]}]}";
        String jsonData= "{\"nodes\":[{\"id\":\"bb975a19-dc07-4649-b584-5b283038941d\",\"type\":\"switch\",\"x\":610,\"y\":290,\"properties\":{\"id\":\"1829396285750931456\",\"type\":\"switch\",\"lock\":\"0\",\"release\":\"0\",\"nodeId\":\"test\",\"nodeName\":\"test\",\"subProductName\":\"测试\",\"ui\":\"node-red\"},\"text\":{\"x\":630,\"y\":292,\"value\":\"test\"}},{\"id\":\"d29c4770-b51b-41e0-bfdf-3bd79e55207a\",\"type\":\"switch\",\"x\":620,\"y\":380,\"properties\":{\"id\":\"1829397868748369920\",\"type\":\"switch\",\"lock\":\"0\",\"release\":\"0\",\"nodeId\":\"test_copy\",\"nodeName\":\"test_copy\",\"subProductName\":\"测试\",\"ui\":\"node-red\"},\"text\":{\"x\":640,\"y\":382,\"value\":\"test_copy\"}},{\"id\":\"d9729aa6-954a-4cda-af6b-383770fc51ec\",\"type\":\"switch\",\"x\":610,\"y\":470,\"properties\":{\"id\":\"1829413755072299008\",\"type\":\"switch\",\"lock\":\"0\",\"release\":\"0\",\"nodeId\":\"g\",\"nodeName\":\"g\",\"subProductName\":\"测试\",\"ui\":\"node-red\"},\"text\":{\"x\":630,\"y\":472,\"value\":\"g\"}},{\"id\":\"e8b57cb9-ccfc-471f-91a5-73b1a63e2e9b\",\"type\":\"switch\",\"x\":620,\"y\":550,\"properties\":{\"id\":\"1838125323892842496\",\"type\":\"switch\",\"lock\":\"0\",\"release\":\"1\",\"nodeId\":\"test_copy_copy\",\"nodeName\":\"test_copy_copy\",\"subProductName\":\"测试\",\"ui\":\"node-red\"},\"text\":{\"x\":640,\"y\":552,\"value\":\"test_copy_copy\"}}],\"edges\":[{\"id\":\"49449434-48ed-4cee-92bc-2d07449e12bb\",\"type\":\"flow-link\",\"sourceNodeId\":\"bb975a19-dc07-4649-b584-5b283038941d\",\"targetNodeId\":\"d29c4770-b51b-41e0-bfdf-3bd79e55207a\",\"startPoint\":{\"x\":590,\"y\":310},\"endPoint\":{\"x\":596.6666666666666,\"y\":360},\"properties\":{},\"pointsList\":[{\"x\":590,\"y\":310},{\"x\":590,\"y\":410},{\"x\":596.6666666666666,\"y\":260},{\"x\":596.6666666666666,\"y\":360}]},{\"id\":\"16ab9539-09d7-4c37-9099-6c825db6cf1c\",\"type\":\"flow-link\",\"sourceNodeId\":\"d29c4770-b51b-41e0-bfdf-3bd79e55207a\",\"targetNodeId\":\"d9729aa6-954a-4cda-af6b-383770fc51ec\",\"startPoint\":{\"x\":596.6666666666666,\"y\":400},\"endPoint\":{\"x\":586.6666666666666,\"y\":450},\"properties\":{},\"pointsList\":[{\"x\":596.6666666666666,\"y\":400},{\"x\":596.6666666666666,\"y\":500},{\"x\":586.6666666666666,\"y\":350},{\"x\":586.6666666666666,\"y\":450}]},{\"id\":\"c6cc7fe7-9677-405d-858f-f3169f17b038\",\"type\":\"flow-link\",\"sourceNodeId\":\"d9729aa6-954a-4cda-af6b-383770fc51ec\",\"targetNodeId\":\"e8b57cb9-ccfc-471f-91a5-73b1a63e2e9b\",\"startPoint\":{\"x\":590,\"y\":490},\"endPoint\":{\"x\":590,\"y\":530},\"properties\":{},\"pointsList\":[{\"x\":590,\"y\":490},{\"x\":590,\"y\":590},{\"x\":590,\"y\":430},{\"x\":590,\"y\":530}]}]}\n";
        System.err.println(jsonData);
        FlowData data = JSON.parseObject(jsonData, FlowData.class);
        Graph graph = new Graph(data);
        GraphInfo graphInfo = graph.toELInfo();
        String result = graphInfo.toString();
        System.out.println(result);

    }


    /*public static void main(String[] args) {
        String json = FlowBuilderContext.NEW().flowType(FlowType.VUE_FLOW)
                .addMappingNodes("nodes1")
                .addMappingEdges("edges1")
                .addMappingNodeId("id")
                .addMappingNodeType("type")
                .addMappingNodeLabel("label")
                .addMappingNodeData("data")
                .addMappingEdgeId("id")
                .addMappingEdgeType("type")
                .addMappingEdgeSource("source")
                .addMappingEdgeTarget("target")
                .addMappingEdgeData("data")
                .getFlowBuilder()
                .format(true)
                .flowJson(getFlowJson())
//                .addNode(MapUtil.builder(new HashMap<String,Object>()).put("id", "1").put("type", "common").put("label", "普通组件").put("data", "{}").build())
//                .addNode(MapUtil.builder(new HashMap<String,Object>()).put("id", "2").put("type", "switch").put("label", "选择组件").put("data", "{}").build())
//                .addEdge(MapUtil.builder(new HashMap<String,Object>()).put("id", "3").put("type", "custom").put("source", "1").put("target", "2").put("data", "{}").build())
                .build();


        System.out.println(json);
    }*/


    private static String getFlowJson(){
        return "{\n" +
                "  \"nodes1\": [\n" +
                "    {\n" +
                "      \"id\": \"f5dce90354ae46b99ca63bff6b0a2d9c\",\n" +
                "      \"type\": \"common\",\n" +
                "      \"position\": {\n" +
                "        \"x\": 130.5,\n" +
                "        \"y\": 117.484375\n" +
                "      },\n" +
                "      \"data\": {\n" +
                "        \"id\": \"\",\n" +
                "        \"type\": \"common\",\n" +
                "        \"name\": \"普通组件\",\n" +
                "        \"style\": {\n" +
                "          \"handles\": {\n" +
                "            \"left\": {\n" +
                "              \"show\": true,\n" +
                "              \"type\": \"source\"\n" +
                "            },\n" +
                "            \"right\": {\n" +
                "              \"show\": true,\n" +
                "              \"type\": \"source\"\n" +
                "            },\n" +
                "            \"top\": {\n" +
                "              \"show\": false,\n" +
                "              \"type\": \"source\"\n" +
                "            },\n" +
                "            \"bottom\": {\n" +
                "              \"show\": false,\n" +
                "              \"type\": \"source\"\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"label\": \"普通组件\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"5a18c8dea2cb4ae58e11963a2e3d0d4a\",\n" +
                "      \"type\": \"switch\",\n" +
                "      \"position\": {\n" +
                "        \"x\": 346.0033261783317,\n" +
                "        \"y\": 50.532903031451156\n" +
                "      },\n" +
                "      \"data\": {\n" +
                "        \"id\": \"\",\n" +
                "        \"type\": \"switch\",\n" +
                "        \"name\": \"选择组件\",\n" +
                "        \"style\": {\n" +
                "          \"handles\": {\n" +
                "            \"left\": {\n" +
                "              \"show\": true,\n" +
                "              \"type\": \"source\"\n" +
                "            },\n" +
                "            \"right\": {\n" +
                "              \"show\": true,\n" +
                "              \"type\": \"source\"\n" +
                "            },\n" +
                "            \"top\": {\n" +
                "              \"show\": false,\n" +
                "              \"type\": \"source\"\n" +
                "            },\n" +
                "            \"bottom\": {\n" +
                "              \"show\": false,\n" +
                "              \"type\": \"source\"\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"label\": \"选择组件\",\n" +
                "      \"parentNode\": \"1bc734a45cbc497f96152547ee3f4e20\",\n" +
                "      \"expandParent\": true\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"1ebdd428538240398348fa3da050e02a\",\n" +
                "      \"type\": \"boolean\",\n" +
                "      \"position\": {\n" +
                "        \"x\": 167.5,\n" +
                "        \"y\": 374.484375\n" +
                "      },\n" +
                "      \"data\": {\n" +
                "        \"id\": \"\",\n" +
                "        \"type\": \"boolean\",\n" +
                "        \"name\": \"布尔组件\",\n" +
                "        \"style\": {\n" +
                "          \"handles\": {\n" +
                "            \"left\": {\n" +
                "              \"show\": true,\n" +
                "              \"type\": \"source\"\n" +
                "            },\n" +
                "            \"right\": {\n" +
                "              \"show\": true,\n" +
                "              \"type\": \"source\"\n" +
                "            },\n" +
                "            \"top\": {\n" +
                "              \"show\": false,\n" +
                "              \"type\": \"source\"\n" +
                "            },\n" +
                "            \"bottom\": {\n" +
                "              \"show\": false,\n" +
                "              \"type\": \"source\"\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"label\": \"布尔组件\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"0d0afe5c032a4ef29496532e709649e7\",\n" +
                "      \"type\": \"for\",\n" +
                "      \"position\": {\n" +
                "        \"x\": 547.5,\n" +
                "        \"y\": 375.484375\n" +
                "      },\n" +
                "      \"data\": {\n" +
                "        \"id\": \"\",\n" +
                "        \"type\": \"for\",\n" +
                "        \"name\": \"次数循环组件\",\n" +
                "        \"style\": {\n" +
                "          \"handles\": {\n" +
                "            \"left\": {\n" +
                "              \"show\": true,\n" +
                "              \"type\": \"source\"\n" +
                "            },\n" +
                "            \"right\": {\n" +
                "              \"show\": true,\n" +
                "              \"type\": \"source\"\n" +
                "            },\n" +
                "            \"top\": {\n" +
                "              \"show\": false,\n" +
                "              \"type\": \"source\"\n" +
                "            },\n" +
                "            \"bottom\": {\n" +
                "              \"show\": false,\n" +
                "              \"type\": \"source\"\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"label\": \"次数循环组件\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"c5d5211dbf2a42ff89e43f6ab29593e2\",\n" +
                "      \"type\": \"iterator\",\n" +
                "      \"position\": {\n" +
                "        \"x\": 1813.234527885277,\n" +
                "        \"y\": 161.93209048566408\n" +
                "      },\n" +
                "      \"data\": {\n" +
                "        \"id\": \"\",\n" +
                "        \"type\": \"iterator\",\n" +
                "        \"name\": \"迭代循环组件\",\n" +
                "        \"style\": {\n" +
                "          \"handles\": {\n" +
                "            \"left\": {\n" +
                "              \"show\": true,\n" +
                "              \"type\": \"source\"\n" +
                "            },\n" +
                "            \"right\": {\n" +
                "              \"show\": true,\n" +
                "              \"type\": \"source\"\n" +
                "            },\n" +
                "            \"top\": {\n" +
                "              \"show\": false,\n" +
                "              \"type\": \"source\"\n" +
                "            },\n" +
                "            \"bottom\": {\n" +
                "              \"show\": false,\n" +
                "              \"type\": \"source\"\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"label\": \"迭代循环组件\",\n" +
                "      \"parentNode\": \"1bc734a45cbc497f96152547ee3f4e20\",\n" +
                "      \"expandParent\": true\n" +
                "    }\n" +
                "  ],\n" +
                "  \"edges1\": [\n" +
                "    {\n" +
                "      \"id\": \"fed7610c79014251a6a1c4e02171078a\",\n" +
                "      \"type\": \"custom\",\n" +
                "      \"source\": \"5a18c8dea2cb4ae58e11963a2e3d0d4a\",\n" +
                "      \"target\": \"c5d5211dbf2a42ff89e43f6ab29593e2\",\n" +
                "      \"sourceHandle\": \"5a18c8dea2cb4ae58e11963a2e3d0d4a__handle-right\",\n" +
                "      \"targetHandle\": \"c5d5211dbf2a42ff89e43f6ab29593e2__handle-left\",\n" +
                "      \"updatable\": true,\n" +
                "      \"data\": {},\n" +
                "      \"label\": \"\",\n" +
                "      \"animated\": true,\n" +
                "      \"markerEnd\": \"arrowclosed\",\n" +
                "      \"sourceX\": 677.0033261783317,\n" +
                "      \"sourceY\": 151.03290303145116,\n" +
                "      \"targetX\": 1802.234527885277,\n" +
                "      \"targetY\": 262.43209048566405\n" +
                "    }\n" +
                "  ],\n" +
                "  \"position\": [\n" +
                "    -196.50910102282512,\n" +
                "    -241.08257626379452\n" +
                "  ],\n" +
                "  \"zoom\": 1.1367874248827985,\n" +
                "  \"viewport\": {\n" +
                "    \"x\": -196.50910102282512,\n" +
                "    \"y\": -241.08257626379452,\n" +
                "    \"zoom\": 1.1367874248827985\n" +
                "  }\n" +
                "}";
    }
}
