# pool4j #
A collection of thread safe resource pool implementations for java.  Resource pools enable you to share resources across classes and/or threads.  Typically resource pools are used when the resource you wish to manage is either expensive to create, or requires a large amount of memory.  The most common resource that is pooled is a database connection.  Pool4j has several pool implementations that provide you the optimal pooling for whatever resource you need to manage.  For a small set of resources that are expensive to create, a `FiniteResourcePool` might be an appropriate choice.  For a set of resources that are cheap to create but memory intensive, a `ScalableResourcePool` might be the appropriate choice.

## Source ##
http://pool4j.googlecode.com/svn/trunk/

## Building ##
[Maven](http://maven.apache.org/) - "mvn install" in the trunk

## Questions ##
Email evanworley@gmail.com