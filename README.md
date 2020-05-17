# pequod-cljs

This is the pequod-cljs project -- a computerized simulation of some of the main components of a non-market, non-command-planning, democratically-planned economy known by the name "participatory economics" (abbreviated "PEX").  

To learn more about participatory economics, please visit [https://www.participatoryeconomics.info](https://www.participatoryeconomics.info).

This project is written in the Clojurescript programming language, and is based on, and named for, a similar project called Pequod (an abbreviation for "Participatory Planning Procedure Prototype"), both as [a project in the Netlogo programming language](https://github.com/msszczep/pequod2), and [as a project in the Clojure programming language](https://github.com/msszczep/pequod-clj).  

Documentation for the rationale of the library and some of its details, as originally articulated, appear in the `docs` directory of this repository.

## Instructions

To run the pequod-cljs code, you will need:

1. A command-line prompt.
2. An up-to-date version of Java (you can check by running `java -version` at your command line).
3. Leiningen.  Instructions for Leiningen are available on the [Leiningen website](https://leiningen.org).

==

To start the Figwheel compiler, navigate to the project folder and run the following command in the terminal:

```
lein figwheel
```

Figwheel will automatically push cljs changes to the browser. The server will be available at [http://localhost:3449](http://localhost:3449) once Figwheel starts up. 

Figwheel also starts `nREPL` using the value of the `:nrepl-port` in the `:figwheel`
config found in `project.clj`. By default the port is set to `7002`.

The figwheel server can have unexpected behaviors in some situations such as when using
websockets. In this case it's recommended to run a standalone instance of a web server as follows:

```
lein do clean, run
```

The application will now be available at [http://localhost:3000](http://localhost:3000).


### Optional development tools

Start the browser REPL:

```
$ lein repl
```
The Jetty server can be started by running:

```clojure
(start-server)
```
and stopped by running:
```clojure
(stop-server)
```

## Running the tests
To run [cljs.test](https://github.com/clojure/clojurescript/blob/master/src/main/cljs/cljs/test.cljs) tests, please use

```
lein doo
```


