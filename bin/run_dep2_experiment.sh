echo "don't forget to update csvgen.clj"
cp ../pequod-big-files/dep1ex61.clj.gz src/clj/pequod_cljs/dep1ex61-big.clj.gz
echo "finished copy"
mv src/clj/pequod_cljs/dep1ex61.clj src/clj/pequod_cljs/dep1ex61-lfs.clj
echo "finished file move"
gunzip src/clj/pequod_cljs/dep1ex61-big.clj.gz
echo "finished gunzip"
mv src/clj/pequod_cljs/dep1ex61-big.clj src/clj/pequod_cljs/dep1ex61.clj
echo "finished file rename; starting csvgen"
lein run -m pequod-cljs.csvgen dep1ex61 > dep2ex61.csv
echo "finished csvgen"
