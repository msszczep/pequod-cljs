# pequod-cljs

This is the pequod-cljs project -- a computerized simulation of some of the main components of a non-market, non-command-planning, democratically-planned economy known by the name "participatory economics" (abbreviated "PEX").  

To learn more about participatory economics, please visit [https://www.participatoryeconomics.info](https://www.participatoryeconomics.info).

This project is written in the Clojure and Clojurescript programming languages, and is based on, and named for, a similar project called Pequod (an abbreviation for "Participatory Planning Procedure Prototype").  Pequod began as [a project in the Netlogo programming language](https://github.com/msszczep/pequod2), and [as a project exclusively in the Clojure programming language](https://github.com/msszczep/pequod-clj).

Documentation for the rationale of the library and some of its details, as originally articulated, may be found in the `docs` directory of this repository.

## Instructions

To run the pequod-cljs code, you will need:

1. A command-line prompt.
2. An up-to-date version of Java (you can check by running `java -version` at your command line).
3. Leiningen.  Instructions for Leiningen are available on the [Leiningen website](https://leiningen.org).

Once you have all of the above, confirm that Leiningen works by running:

```
lein --version
```

You should see a response that reads something like this:

```
Leiningen 2.8.1 on Java 11.0.7 OpenJDK 64-Bit Server VM
```

Ensure that the libraries needed for running pequod-cljs by navigating to the project folder and running:

```
lein deps
```

This repository simulates the participatory planning procedure in a simulated participatory economy as a web app or via the command line.  To run it as a web app, start the Figwheel compiler, whhich is done by navigating to the project folder and run the following command in the terminal:

```
lein figwheel
```

## Figwheel

Once Figwheel starts, you can view the pequod-cljs web app locally at [http://localhost:3449](http://localhost:3449).

Figwheel will automatically push cljs changes to the browser.

Figwheel also starts `nREPL` using the value of the `:nrepl-port` in the `:figwheel`
config found in `project.clj`. By default the port is set to `7002`.

The figwheel server can have unexpected behaviors in some situations such as when using
websockets. In this case it's recommended to run a standalone instance of a web server as follows:

```
lein do clean, run
```

## Tests

To run [cljs.test](https://github.com/clojure/clojurescript/blob/master/src/main/cljs/cljs/test.cljs) tests, please use

```
lein doo
```


