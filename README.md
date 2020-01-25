# pequod-cljs

This is the pequod-cljs project -- a Clojurescript rewrite of the Pequod library, which simulates the participatory planning procedure of a non-market, non-command-planning, democratically planned participatory eco nomy (parecon, or PEX).

Documentation for the rationale of the library and some of its details, as originally articulated, appear in the `docs` directory of this repository.

Some functionality in pequod-cljs was modeled on the [pequod-clj library](https://github.com/msszczep/pequod-clj) and the [pequod2 library in Netlogo](https://github.com/msszczep/pequod2). 

## Development mode

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


