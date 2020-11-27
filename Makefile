launch:
	emacs src/cljs/pequod_cljs/core.cljs &
	lein figwheel

core:
	emacs src/cljs/pequod_cljs/core.cljs &

csv:
	emacs src/clj/pequod_cljs/csvgen.clj &

test:
	lein test

# make councils NAMESPACE=ex001
councils:
	lein run -m pequod-cljs.gen $(NAMESPACE) > src/cljs/pequod_cljs/$(NAMESPACE).cljs

doo:
	emacs src/cljs/pequod_cljs/core.cljs &
	emacs test/cljs/pequod_cljs/core_test.cljs &
	lein doo

data:
	emacs src/clj/pequod_cljs/data.clj &

pgd:
	emacs src/cljs/pequod_cljs/ex001ppgdata.cljs &

clean:
	lein clean
	rm -f figwheel_server.log
