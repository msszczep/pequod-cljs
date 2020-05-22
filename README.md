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

Once Figwheel starts, you can view the pequod-cljs web app locally at [http://localhost:3449](http://localhost:3449).

## Using the Webapp

There are a number of buttons along the top row of the webapp.  There is a dropdown at the left of the
row to select any of a number of experiments.  To run the app, first select an experiment from this
dropdown.

Next, press the button marked "Setup".  This loads the data -- the worker councils, consumer councils,
and other settings -- into the app.  You can iterate through the participatory planning procedure
with the buttons "Iterate 1X", "Iterate 5X", "Iterate 10X", and "Iterate 50X".  

If you are interested in seeing the values for a given council at any point in the iterative process, 
you can use the Council Explorer.  Select "wcs" (workers councils) or "ccs" (consumer councils) then
type a number in the adjacent text field (all councils are identified by number, starting with one and
counting up) and click the "Show Council" button.  The council should appear in the Council Explorer
portion of the page as a Clojure map.

After the economy reaches its convergence threshold (more about that below), you may "reset" the economy
with the "Augmented reset" button.  This button is intended to simulate technology enhancement within
an economy over the course of a year and has the following effects:

1. Adding a small random augment to the coefficients of each workers' council.
2. Adding a small random augment to the coefficients of each consumers' council.
3. Reset the iteration counter back to zero.

Once it's reset, you can iterate through the participatory planning procedure again and compare the results
with those of the previous series of iterations.

Below the row of buttons, there are a number of additional values abbreviated and explained as follows:

* `WCs`: A count of the number of worker councils loaded into the program
* `CCs`: A count of the number of consumer councils loaded into the program
* `TH`: The convergence threshold loaded into the program
* `A-GDP TY:` The gross domestic product for this year (the current sequence of iterations)
* `A-GDP LY:` The gross domestic product for last year (this activates once the "Augumented reset" button is clicked)
* `A-GDP AVG:` The average of the two gross domestic product calculations, when applicable

To reset the app, simply refresh the page.  Note that iterations only proceed in one trajectory; if you 
want to "rewind" you must refresh the page and repeat the process.

## Using the Command Line

TODO

## Figwheel

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


