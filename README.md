# pequod-cljs

This is the pequod-cljs project -- a computerized simulation of some of the main components of a non-market, non-command-planning, democratically-planned economy known as a "participatory economy".

To learn more, please visit [https://www.participatoryeconomy.org](https://www.participatoryeconomy.org).

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
* `A-GDP TY:` The gross domestic product for this year (the current sequence of iterations)
* `A-GDP LY:` The gross domestic product for last year (this activates once the "Augumented reset" button is clicked)
* `A-GDP AVG:` The average of the two gross domestic product calculations, when applicable

Most of the page is a chart showing five categories of goods along the top and statistics for each good.

The prices refer to indicative prices within a participatory economy.  PDList refers to "price deltas" 
-- the rate of change for a given price, currently capped at 25%.  "New deltas" refers to the new price
change after an iteration.  

The row entitled "Percent Threshold / Threshold met?" is your barometer as to when the iterations
are "done" (there's no block for continuing iterations; conceivably it could run forever).  The row 
changes color to the following:

* blue if all the surpluses (the difference between supply and demand, as a percentage) 
for that category are below 3%
* green if all the surpluses are below 5%
* yellow if all the surpluses are below 10%
* organe if all the surpluses are below 20%
* red otherwise

To reset the app, simply refresh the page.  Note that iterations only proceed in one trajectory; if you 
want to "rewind", you must refresh the page and repeat the process.

## Using the Command Line

There is a Clojure clone of the pequod-cljs code, called `csvgen`, which can generate CSV output of a 
participatory planning process.

To use `csvgen` from the command line, run:

`lein run -m pequod-cljs.csvgen [namespace]`

...where `[namespace]` is the namespace of the data file whose contents you're using for the iteration.
Note: `csvgen` uses `.clj` source files in the `src/clj/pequod_cljs/` directory, and `csvgen` delivers 
its output to STDOUT, so it is advisable to redirect the output into a file, as in:

`lein run -m pequod-cljs.csvgen experiment01 > experiment01.csv`

The output, from left to right, is the iteration, threshold color, all the collective metrics presented
in the pequod-cljs webapp, one column at a time, along with the effort score and output score of all
councils, both for a given "year" and an augmented "year".

There is a parameter in the `-main` function in `csvgen.clj` called `toothaches?`.  If you set `toothaches?` to `true`,
you artificially augment the results forcing increasing returns to scale.

## Generating/using new experiments for the webapp

To generate new experiments, use the command:

```
lein run -m pequod-cljs.gen [namespace] > [namespace].cljs
```

where `[namespace]` is the name of the namespace and the file to generate.

The parameters for generating workers councils and consumer councils may be themselves adjusted in the files:

```
src/clj/pequod_cljs/wcs.clj
```

and

```
src/clj/pequod_cljs/ccs.clj
```

You can adjust the number of councils generated with the calls to `create-ccs-bulk` and `create-wcs-bulk` as seen in the
file:

```
src/clj/pequod_cljs/gen.clj
```

The number of worker councils is determined in each of three arguments for each of three "industries" (public goods, private
goods, intermediate goods) in `create-wcs-bulk`.  The number of consumer councils is the value of the first argument in
`create-ccs-bulk`.

The newly-created experiment(s) can then be invoked in either of two ways:

1.  Through the webapp, by placed the experiment with a `.cljs` extension in the `src/cljs/pequod_cljs` directory
and updating the `src/cljs/pequod_cljs/core.cljs` file.  `core.cljs` would need three changes: adding in the namespace,
adding the invocation of the new namespace in the `wcs` data and `ccs` data in the `setup` function, and adding in an option
for the namespace in the experimentation dropdown in the `all-buttons` function.

2.  Through the `csvgen` utility, by placed the experiment renamed with a `.clj` extension in the `src/clj/pequod_cljs` directory
and updating the `src/clj/pequod_cljs/csvgen.clj` file.  `csvgen.clj` would need two changes: adding in the namespace,
and adding the invocation of the `wcs` data and `ccs` data in the `setup` function.

## Experiments from _Democratic Economic Planning_

This software package was used to generate and test experiments referred to in the book _Democratic Economic Planning_
by Robin Hahnel (Routledge, 2021).  You can download the files:

[http://www.szcz.org/depexperiments/](http://www.szcz.org/depexperiments)

If you wish to use the file in this application, download the files you wish to use from the link above, use `gunzip`
to uncompress the files, place them in the appropriate directory and make the adjustments as noted above for using new
experiments.

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

To run the automated tests in pequod-cljs.util-test, run:

```
lein test
```

## Update package-lock.json

The Javascript libraries referenced in the `package-lock.json` file can be updated with this command:

```
npm install --package-lock-only [LIBRARY_NAME_GOES_HERE] 
```
