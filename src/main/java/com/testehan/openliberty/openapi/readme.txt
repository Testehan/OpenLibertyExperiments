Before you proceed, note that the 1.0 version of the MicroProfile OpenAPI specification does not define how
the /openapi endpoint may be partitioned in the event of multiple JAX-RS applications running on the same server. In
other words, you must stick to one JAX-RS application per server instance as the behaviour for handling multiple
applications is currently undefined.

    http://localhost:9080/openapi           - just simple text visualisation
    http://localhost:9080/openapi/ui        - very nice UI interface
        seems to be loaded ok even if I have multiple applications (by application i mean classes annotated with  @ApplicationPath)


mp.openapi.scan.disable = true     // if you use this then the content from file openapi.yaml will be used

