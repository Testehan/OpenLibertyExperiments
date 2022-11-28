http://localhost:9080/OpenLibertyExperiments/inventory2/hosts

    should return something like:

{"*":[{"href":"http://localhost:9080/OpenLibertyExperiments/inventory2/hosts/*","rel":"self"}],
"localhost":[{"href":"http://localhost:9080/OpenLibertyExperiments/inventory2/hosts/localhost","rel":"self"},
            {"href":"http://localhost:9080/OpenLibertyExperiments/system/properties","rel":"properties"}]}

    if it doesn't you can add the local host information by calling

http://localhost:9080/OpenLibertyExperiments/inventory2/hosts/localhost