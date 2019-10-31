(ns pequod-cljs.ex003)

(def ccs
  '({:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.097014705505415
    0.10598807831757415
    0.12314564850702867
    0.10565329719046188],
   :final-demands [0 0 0 0 0],
   :cy 9.243749495590524,
   :public-good-exponents [0.16562915966508993 0.08853638866870253],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.11763600572298705
    0.10817666564836026
    0.11399548796569776
    0.16071504507523673],
   :final-demands [0 0 0 0 0],
   :cy 6.18744843743377,
   :public-good-exponents [0.11272329826516005 0.1361955600535416],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.1295933194365289
    0.11881053871413003
    0.1342078551693194
    0.08547663018723371],
   :final-demands [0 0 0 0 0],
   :cy 14.775870752169338,
   :public-good-exponents [0.14272072910966535 0.15663907389306136],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.15070773609072402
    0.13329485585477074
    0.13880168681526878
    0.1452696375658749],
   :final-demands [0 0 0 0 0],
   :cy 6.064118589307656,
   :public-good-exponents [0.09775175898963627 0.15559753532886217],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.14597152084786946
    0.09411723462193547
    0.14836365468158338
    0.11431738821634882],
   :final-demands [0 0 0 0 0],
   :cy 6.345762714757149,
   :public-good-exponents [0.08776246642622595 0.11104889339686518],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.16600771775147646
    0.12835024282531832
    0.1334736611773485
    0.1488318440092557],
   :final-demands [0 0 0 0 0],
   :cy 13.903524601663445,
   :public-good-exponents [0.1055944096941593 0.08899305638454083],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.08758000702360495
    0.1015842934183431
    0.09434826289538162
    0.08540341153517461],
   :final-demands [0 0 0 0 0],
   :cy 13.664252227029428,
   :public-good-exponents [0.16341042044191312 0.13943668031626463],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.08898524765553575
    0.13416832943248694
    0.16558134721774595
    0.1333459230287926],
   :final-demands [0 0 0 0 0],
   :cy 14.621232908613786,
   :public-good-exponents [0.15335297962591132 0.11459015936926192],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.09523500004290172
    0.12490652201748285
    0.15106120645656856
    0.08438693642962553],
   :final-demands [0 0 0 0 0],
   :cy 6.076049697205666,
   :public-good-exponents [0.12806615861965104 0.08785113768191632],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.15794272260885378
    0.11493526306423044
    0.15659519458262122
    0.11617432981621688],
   :final-demands [0 0 0 0 0],
   :cy 7.291430740492516,
   :public-good-exponents [0.154650275058242 0.10044396350138994],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.13351714150516458
    0.147594144225811
    0.16424589669912762
    0.10490434035481926],
   :final-demands [0 0 0 0 0],
   :cy 11.307025284457687,
   :public-good-exponents [0.1569443333081992 0.14372107314464652],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.14269306790649355
    0.16473985941982305
    0.10956127790056758
    0.11974698812172313],
   :final-demands [0 0 0 0 0],
   :cy 9.310178191726775,
   :public-good-exponents [0.0931594548980924 0.09138232292341562],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.13188231546159795
    0.16007633731209306
    0.15570053410535656
    0.14919189030645552],
   :final-demands [0 0 0 0 0],
   :cy 11.773975992228532,
   :public-good-exponents [0.10559673306432923 0.16274647835409195],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.08416810549327881
    0.15001136445103497
    0.16289928129016804
    0.13190493905097922],
   :final-demands [0 0 0 0 0],
   :cy 8.32871114739227,
   :public-good-exponents [0.15241391130481313 0.13530696951081378],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.11332931791387583
    0.12823540665484034
    0.1467210761315068
    0.08786411600551525],
   :final-demands [0 0 0 0 0],
   :cy 8.202102214563487,
   :public-good-exponents [0.08505032317226828 0.15283685876034578],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.08737094656229873
    0.15696657582942103
    0.116570819111744
    0.11805008393128427],
   :final-demands [0 0 0 0 0],
   :cy 6.371966117909631,
   :public-good-exponents [0.1562124508722591 0.15589158785380136],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.0969823390499293
    0.10894636966993765
    0.144151956483642
    0.15842311006696136],
   :final-demands [0 0 0 0 0],
   :cy 6.236680691900727,
   :public-good-exponents [0.10646149870473329 0.09947312022935854],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.1305454360360634
    0.09238893957789751
    0.08537386815772198
    0.13433703036680045],
   :final-demands [0 0 0 0 0],
   :cy 8.173297914188254,
   :public-good-exponents [0.15866459008602102 0.1343741069072205],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.12681996393513928
    0.08515860995058244
    0.14098223290695072
    0.14139333616814376],
   :final-demands [0 0 0 0 0],
   :cy 10.230098876937397,
   :public-good-exponents [0.15760623263352644 0.0894615223238571],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.0971226656042895
    0.1285677020231387
    0.12782345696772263
    0.14640186348415973],
   :final-demands [0 0 0 0 0],
   :cy 7.613559023691884,
   :public-good-exponents [0.12082525166390981 0.11202819020852513],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.12535178694561105
    0.11711846123865602
    0.109072374083094
    0.10368352362344312],
   :final-demands [0 0 0 0 0],
   :cy 12.071198970029519,
   :public-good-exponents [0.12449175655826179 0.09762479317216229],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.09443572424855563
    0.1537569568737459
    0.16627639760738933
    0.15675597409264383],
   :final-demands [0 0 0 0 0],
   :cy 8.693685722007846,
   :public-good-exponents [0.1355050921208109 0.09891577710614041],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.12006980969912441
    0.1440727213141958
    0.1251015211862249
    0.08853768240243995],
   :final-demands [0 0 0 0 0],
   :cy 12.360326279183603,
   :public-good-exponents [0.09950504522835105 0.09980265875213096],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.09672898613808893
    0.11087526000077239
    0.10726956523786448
    0.08612768417938198],
   :final-demands [0 0 0 0 0],
   :cy 10.183938498747683,
   :public-good-exponents [0.08692616048809272 0.1553255490360851],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.09265175528954399
    0.08394824348048607
    0.16269772538305532
    0.13891308575581432],
   :final-demands [0 0 0 0 0],
   :cy 11.15204917012904,
   :public-good-exponents [0.12399485096731575 0.11536770674518947],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.09797728862018268
    0.16439875123345665
    0.09491003127332198
    0.13126263031669858],
   :final-demands [0 0 0 0 0],
   :cy 12.76612598444888,
   :public-good-exponents [0.11212639667435112 0.15212101141577083],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.08990775237023914
    0.10146327954957625
    0.10670525021352657
    0.1467994043170862],
   :final-demands [0 0 0 0 0],
   :cy 9.04151656848754,
   :public-good-exponents [0.12394516398605629 0.1273813286588354],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.08431215203734709
    0.09661950928582122
    0.1450144874760418
    0.08778852507556242],
   :final-demands [0 0 0 0 0],
   :cy 10.450490734167964,
   :public-good-exponents [0.12292737634709236 0.15869286808109],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.14898672373194327
    0.1257728204556131
    0.08547677867605397
    0.11134462783041779],
   :final-demands [0 0 0 0 0],
   :cy 11.385062398147117,
   :public-good-exponents [0.08393918247517201 0.12775374166979986],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.1359225001725239
    0.1050011416351648
    0.1174671503718043
    0.08524241319548348],
   :final-demands [0 0 0 0 0],
   :cy 8.43634146656939,
   :public-good-exponents [0.11338388168370475 0.15820871170552014],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.13978057216358852
    0.15595343911728304
    0.1636844285551895
    0.09364408242877834],
   :final-demands [0 0 0 0 0],
   :cy 9.668000733694281,
   :public-good-exponents [0.13817474098972027 0.1435560144075732],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.11214097270927159
    0.09902639762333174
    0.12386198634265118
    0.12302075334249439],
   :final-demands [0 0 0 0 0],
   :cy 12.571746632857533,
   :public-good-exponents [0.09060392015516114 0.10499456321784911],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.13940836021045921
    0.14041968461990578
    0.12255766502696619
    0.11996595910202357],
   :final-demands [0 0 0 0 0],
   :cy 13.381546830282387,
   :public-good-exponents [0.09635412506613955 0.11767525167350994],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.15168524496574232
    0.15336414918518393
    0.10698487436115597
    0.1196887320237187],
   :final-demands [0 0 0 0 0],
   :cy 7.626274906069236,
   :public-good-exponents [0.14699246084920325 0.1443550543440933],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.1514944959168455
    0.10475010416007516
    0.1033876802734531
    0.1041402892851423],
   :final-demands [0 0 0 0 0],
   :cy 8.58725078726028,
   :public-good-exponents [0.10005300541374106 0.13867662053493318],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.11682761581503659
    0.08979238190901861
    0.09413230286563366
    0.08819261139604871],
   :final-demands [0 0 0 0 0],
   :cy 8.742397532707717,
   :public-good-exponents [0.14481645596192247 0.1452035602705189],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.09494584765063635
    0.15822587560741902
    0.1633683142873313
    0.10259451420068802],
   :final-demands [0 0 0 0 0],
   :cy 10.643956339473288,
   :public-good-exponents [0.1338002458792456 0.13923148274598282],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.12406931844532923
    0.1124130041203511
    0.13720276529787936
    0.12021447576959071],
   :final-demands [0 0 0 0 0],
   :cy 9.493141291950563,
   :public-good-exponents [0.13765397170521765 0.13036410612781524],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.09942718505457966
    0.14047742061267793
    0.15406145308394148
    0.11100018565067024],
   :final-demands [0 0 0 0 0],
   :cy 13.283642451314744,
   :public-good-exponents [0.15643802143994318 0.09730551723877143],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.13497514686538614
    0.16333607256500227
    0.09280572806579242
    0.11892228874052947],
   :final-demands [0 0 0 0 0],
   :cy 12.810143415047534,
   :public-good-exponents [0.09607386488236204 0.1245636653724214],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.13964968043632398
    0.1189792787646649
    0.08707430668319104
    0.15294749370888083],
   :final-demands [0 0 0 0 0],
   :cy 14.455046083375091,
   :public-good-exponents [0.08815138386297455 0.09381372073937529],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.1521120993540815
    0.14882099754124226
    0.0843375339521614
    0.11683579430942415],
   :final-demands [0 0 0 0 0],
   :cy 9.818558943644538,
   :public-good-exponents [0.08941509453047972 0.10943644965399199],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.11624692714472276
    0.16470568317200576
    0.14958439216929587
    0.1268886925856544],
   :final-demands [0 0 0 0 0],
   :cy 13.635990506124555,
   :public-good-exponents [0.14403590585780757 0.09996932030602272],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.12628867922738363
    0.09153920738525383
    0.11675683686435513
    0.13949275503355077],
   :final-demands [0 0 0 0 0],
   :cy 7.031307737830404,
   :public-good-exponents [0.10623946364062321 0.10319686996580006],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.09964697189617894
    0.09285612768081226
    0.10788582272683164
    0.1021796240075644],
   :final-demands [0 0 0 0 0],
   :cy 13.847073069846083,
   :public-good-exponents [0.11562862520676558 0.0842325466180624],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.16325461406534403
    0.1131044894463361
    0.10215996280934178
    0.16084234169874323],
   :final-demands [0 0 0 0 0],
   :cy 6.91979581737855,
   :public-good-exponents [0.11668503725084226 0.09046419327656609],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.15525978381584118
    0.11496875829206119
    0.16113712060384086
    0.14961437997204707],
   :final-demands [0 0 0 0 0],
   :cy 7.502857033724554,
   :public-good-exponents [0.13661812331486967 0.16241092459776652],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.15326255523249827
    0.15364848232833206
    0.14970449049112794
    0.1481678417483058],
   :final-demands [0 0 0 0 0],
   :cy 12.143291929858549,
   :public-good-exponents [0.14447181434434148 0.15190457768374938],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.1526965399302957
    0.09990334088216912
    0.15901930819058854
    0.11526240979093018],
   :final-demands [0 0 0 0 0],
   :cy 13.805918111234664,
   :public-good-exponents [0.11770404950817817 0.11170603011315577],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.1406382569420011
    0.16558328160554664
    0.14494221629845155
    0.10961033039639913],
   :final-demands [0 0 0 0 0],
   :cy 9.533172342379594,
   :public-good-exponents [0.14295213075597768 0.14133345218491422],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.15090192133406966
    0.09869402657507925
    0.13402227886467719
    0.1260397172731803],
   :final-demands [0 0 0 0 0],
   :cy 10.339822075517661,
   :public-good-exponents [0.14609733185347237 0.10796305682727457],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.11813481492747063
    0.08344795481734918
    0.1318419678563327
    0.16169607241566222],
   :final-demands [0 0 0 0 0],
   :cy 9.686819907974726,
   :public-good-exponents [0.09509070656026526 0.11187075094511778],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.10453472586630248
    0.16023477330076596
    0.12926598057741734
    0.15129290018019742],
   :final-demands [0 0 0 0 0],
   :cy 13.237077322600053,
   :public-good-exponents [0.09414891539032579 0.12116895772438277],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.08539678691918286
    0.16477731704704032
    0.13317251727660143
    0.13640304659648517],
   :final-demands [0 0 0 0 0],
   :cy 8.921424504853166,
   :public-good-exponents [0.10766111397182301 0.0970806475610731],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.1222700027142421
    0.14024548502261336
    0.13773566383083824
    0.14691890584554396],
   :final-demands [0 0 0 0 0],
   :cy 8.09685790971767,
   :public-good-exponents [0.16218218644565316 0.08612875981092573],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.13385807768850183
    0.08689564099998714
    0.13326324835422473
    0.10988301123252393],
   :final-demands [0 0 0 0 0],
   :cy 13.609559923012437,
   :public-good-exponents [0.13147097284996873 0.151913120024257],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.15475157128482536
    0.1092100991330757
    0.11157832932360037
    0.11834666386712775],
   :final-demands [0 0 0 0 0],
   :cy 14.186287426010319,
   :public-good-exponents [0.12313710503665311 0.14881699620710964],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.13269312193990007
    0.11896751018439483
    0.11840872789249397
    0.11203732823327604],
   :final-demands [0 0 0 0 0],
   :cy 6.102330199533014,
   :public-good-exponents [0.112614503003795 0.09976000673764829],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.11160401231878202
    0.15953652752561048
    0.1659213207453724
    0.16572099554535563],
   :final-demands [0 0 0 0 0],
   :cy 9.281640382598068,
   :public-good-exponents [0.1128737557684543 0.13406426313005132],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.15637315524067158
    0.1481478178621724
    0.16393231551546428
    0.11735326182911032],
   :final-demands [0 0 0 0 0],
   :cy 9.625092045578915,
   :public-good-exponents [0.11695812666544561 0.08884588408116048],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.15336459590316487
    0.12177711937811386
    0.15552381283645283
    0.08449562626134272],
   :final-demands [0 0 0 0 0],
   :cy 13.3671266252699,
   :public-good-exponents [0.10392982603384687 0.16539923959198322],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.1592770643535775
    0.12445133182426651
    0.156262837674078
    0.15544109204765863],
   :final-demands [0 0 0 0 0],
   :cy 8.58517131635739,
   :public-good-exponents [0.16297568243316368 0.08520326205880216],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.11697087966342623
    0.1624039761394049
    0.11335439469447237
    0.16315817944332073],
   :final-demands [0 0 0 0 0],
   :cy 11.806074896011504,
   :public-good-exponents [0.12925437148513289 0.09408223134189175],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.1471545701874677
    0.09360348788248293
    0.10738575961301906
    0.11784256365944734],
   :final-demands [0 0 0 0 0],
   :cy 14.587321509082797,
   :public-good-exponents [0.11517019690847702 0.13874722711404952],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.09966440016057221
    0.14121623363348063
    0.08487238779032258
    0.15376296254860175],
   :final-demands [0 0 0 0 0],
   :cy 10.201245293075743,
   :public-good-exponents [0.14026202436131 0.13951812600328156],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.10551272111681155
    0.11547225730166377
    0.12360799831246116
    0.1205859219422312],
   :final-demands [0 0 0 0 0],
   :cy 14.334599769374654,
   :public-good-exponents [0.09781133053406255 0.14215522014218232],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.09937704108777773
    0.11748241500138863
    0.10958586344263294
    0.14894707958672354],
   :final-demands [0 0 0 0 0],
   :cy 11.058512103660728,
   :public-good-exponents [0.11952040073271042 0.16619505856011274],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.10115934677817692
    0.12228961707211947
    0.11928142926523196
    0.11655901449906195],
   :final-demands [0 0 0 0 0],
   :cy 12.270323751130187,
   :public-good-exponents [0.08789977148174902 0.12280051602828955],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.16395902879386626
    0.1663963094758687
    0.16464757676227743
    0.16241826074207294],
   :final-demands [0 0 0 0 0],
   :cy 12.323957267654535,
   :public-good-exponents [0.1304577904937112 0.1170868774746836],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.08614265884284555
    0.0975633245575947
    0.08434513164811631
    0.16138354586483838],
   :final-demands [0 0 0 0 0],
   :cy 14.858243169757912,
   :public-good-exponents [0.12778339017432455 0.09155083369315077],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.1358304578822193
    0.12167793221750492
    0.13668598502163004
    0.11995127973887312],
   :final-demands [0 0 0 0 0],
   :cy 12.563074003936139,
   :public-good-exponents [0.09902356293426676 0.14009872222501804],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.09928498949083546
    0.1532417722581177
    0.1519742602666783
    0.14833024050579713],
   :final-demands [0 0 0 0 0],
   :cy 12.417236411325245,
   :public-good-exponents [0.11249239424659799 0.16089963912517696],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.15387590786093724
    0.11833401658467829
    0.11201311691590976
    0.11379273387847712],
   :final-demands [0 0 0 0 0],
   :cy 9.848739048512098,
   :public-good-exponents [0.09685484915470066 0.14279848433109776],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.09818671887935865
    0.1614518774123816
    0.10079079316223885
    0.12721908709009527],
   :final-demands [0 0 0 0 0],
   :cy 10.334887812109685,
   :public-good-exponents [0.10308573041350091 0.0846270881493466],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.10147737387345009
    0.13317957744483377
    0.13497183770356802
    0.16261961775356376],
   :final-demands [0 0 0 0 0],
   :cy 14.60020413312448,
   :public-good-exponents [0.15187773393547782 0.16585780364061964],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.1262296873940613
    0.13651453615906686
    0.13584796804124244
    0.1567434798420529],
   :final-demands [0 0 0 0 0],
   :cy 12.739199360006944,
   :public-good-exponents [0.12871867438755163 0.1327095147871455],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.09989128931832865
    0.11467761104067847
    0.1423887493559832
    0.16538352267197143],
   :final-demands [0 0 0 0 0],
   :cy 13.618756436063084,
   :public-good-exponents [0.11565007987091303 0.13697647285161801],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.08465014067840729
    0.14539206843012475
    0.10495173516482889
    0.13985699613513358],
   :final-demands [0 0 0 0 0],
   :cy 9.564337893496498,
   :public-good-exponents [0.1396849358523382 0.16480330541754665],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.14585645297509137
    0.11059850148769294
    0.10569875035606663
    0.13088022854574283],
   :final-demands [0 0 0 0 0],
   :cy 12.269002664960654,
   :public-good-exponents [0.14879301969187508 0.12022470341662184],
   :public-good-demands [0],
   :income 5000}
  {:effort 1,
   :num-workers 10,
   :utility-exponents
   [0.1509140964102863
    0.09074848046699543
    0.10049124734356696
    0.14228833502578392],
   :final-demands [0 0 0 0 0],
   :cy 9.706474515033491,
   :public-good-exponents [0.16111129876652358 0.08601932783805505],
   :public-good-demands [0],
   :income 5000}))

(def wcs 
  '({:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.12879407342644827],
   :industry 2,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 1,
   :labor-quantities [0],
   :production-inputs [[1 2 3 4] [1 2] [2]],
   :input-exponents
   [0.09640409396470272
    0.13984868175074927
    0.07340102914778301
    0.10356975041080194],
   :xe 0.05,
   :nature-exponents [0.10260108000542659 0.13984241917673837],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.18401681317192473 0.18989030332538148],
   :industry 2,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 1,
   :labor-quantities [0],
   :production-inputs [[3] [1] [1 2]],
   :input-exponents [0.24756237883572707],
   :xe 0.05,
   :nature-exponents [0.2478242121729258],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.06442086122481089 0.11794620224375711],
   :industry 2,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 1,
   :labor-quantities [0],
   :production-inputs [[1 2 3 4] [1 2] [1 2]],
   :input-exponents
   [0.12172040405080342
    0.11737829639852825
    0.11951376992485413
    0.07971362224777587],
   :xe 0.05,
   :nature-exponents [0.11628963218690344 0.09319360118961874],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.12282604861928653],
   :industry 2,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 1,
   :labor-quantities [0],
   :production-inputs [[1 3 4] [2] [2]],
   :input-exponents
   [0.1668507097687211 0.1115183632334328 0.1962009731268777],
   :xe 0.05,
   :nature-exponents [0.13254450949516525],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.15037911897609219],
   :industry 2,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 1,
   :labor-quantities [0],
   :production-inputs [[1] [1 2] [2]],
   :input-exponents [0.2017731945959878],
   :xe 0.05,
   :nature-exponents [0.2098459232104664 0.23918333627589083],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.15187488578272387],
   :industry 2,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 1,
   :labor-quantities [0],
   :production-inputs [[1 2 3] [2] [2]],
   :input-exponents
   [0.19287138333174922 0.10781189082664205 0.13465527484971185],
   :xe 0.05,
   :nature-exponents [0.13370976498815723],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.13146893936776605 0.19771156495069978],
   :industry 2,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 1,
   :labor-quantities [0],
   :production-inputs [[4] [2] [1 2]],
   :input-exponents [0.15667324471018176],
   :xe 0.05,
   :nature-exponents [0.23112742598551167],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.17061108661497526],
   :industry 2,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 1,
   :labor-quantities [0],
   :production-inputs [[3] [1] [1]],
   :input-exponents [0.23363955128944752],
   :xe 0.05,
   :nature-exponents [0.223810913143307],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.18533422834834892],
   :industry 2,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 1,
   :labor-quantities [0],
   :production-inputs [[2 4] [1] [2]],
   :input-exponents [0.22882881290850973 0.21805598318219033],
   :xe 0.05,
   :nature-exponents [0.20507066896242643],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.148051289184851 0.1386993602882511],
   :industry 2,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 1,
   :labor-quantities [0],
   :production-inputs [[1] [1 2] [1 2]],
   :input-exponents [0.13112806536658456],
   :xe 0.05,
   :nature-exponents [0.15051455103026537 0.1118542788212683],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.08861808099664832],
   :industry 2,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 2,
   :labor-quantities [0],
   :production-inputs [[1 2 3 4] [1] [2]],
   :input-exponents
   [0.1609299601394108
    0.14970076059071713
    0.14048521726687035
    0.137638668930485],
   :xe 0.05,
   :nature-exponents [0.15511799886233074],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.13170600200722887 0.08688588679748933],
   :industry 2,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 2,
   :labor-quantities [0],
   :production-inputs [[1 2 4] [2] [1 2]],
   :input-exponents
   [0.09242337424968973 0.11353603495354112 0.1566102408942634],
   :xe 0.05,
   :nature-exponents [0.12151506448160368],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.22496129271762189 0.2134696473272163],
   :industry 2,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 2,
   :labor-quantities [0],
   :production-inputs [[1] [2] [1 2]],
   :input-exponents [0.19933710382242859],
   :xe 0.05,
   :nature-exponents [0.1607639060620099],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.11678232508426595 0.10505026272875914],
   :industry 2,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 2,
   :labor-quantities [0],
   :production-inputs [[1 2 3 4] [1 2] [1 2]],
   :input-exponents
   [0.0841449655969258
    0.07399759677951573
    0.09966025816193397
    0.09350493032926496],
   :xe 0.05,
   :nature-exponents [0.11836000506385952 0.0901930815501144],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.10895782885698113],
   :industry 2,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 2,
   :labor-quantities [0],
   :production-inputs [[1 2 3 4] [2] [2]],
   :input-exponents
   [0.15271815970520763
    0.14973098924783812
    0.14833853409542397
    0.09771312050823479],
   :xe 0.05,
   :nature-exponents [0.13263407855260845],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.1528533654766981 0.1590091379134701],
   :industry 2,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 2,
   :labor-quantities [0],
   :production-inputs [[3 4] [2] [1 2]],
   :input-exponents [0.1541880618087731 0.17553457112012924],
   :xe 0.05,
   :nature-exponents [0.18354212458200897],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.10116897471136799],
   :industry 2,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 2,
   :labor-quantities [0],
   :production-inputs [[1 2 3 4] [1] [2]],
   :input-exponents
   [0.1046751339650627
    0.08895524650000897
    0.12082014266731422
    0.14127135543540248],
   :xe 0.05,
   :nature-exponents [0.08437649162351663],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.14462754019444474],
   :industry 2,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 2,
   :labor-quantities [0],
   :production-inputs [[2 3] [2] [1]],
   :input-exponents [0.17084348870325172 0.2063548866297597],
   :xe 0.05,
   :nature-exponents [0.15115464884046714],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.16753887649858365],
   :industry 2,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 2,
   :labor-quantities [0],
   :production-inputs [[1 2] [1 2] [1]],
   :input-exponents [0.1173571871856562 0.1314286086023413],
   :xe 0.05,
   :nature-exponents [0.1195727288050827 0.13193067214033719],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.15045448198896644],
   :industry 2,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 2,
   :labor-quantities [0],
   :production-inputs [[3] [1 2] [2]],
   :input-exponents [0.1551511138323975],
   :xe 0.05,
   :nature-exponents [0.23016964821431382 0.17224020864409964],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.1174835441413952 0.07278731740996183],
   :industry 1,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 1,
   :labor-quantities [0],
   :production-inputs [[1 2 3 4] [1] [1 2]],
   :input-exponents
   [0.08549367549368744
    0.13117536607635522
    0.07840421624403286
    0.0745771144069987],
   :xe 0.05,
   :nature-exponents [0.11777653739963695],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.1782138342625695],
   :industry 1,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 1,
   :labor-quantities [0],
   :production-inputs [[1 4] [2] [2]],
   :input-exponents [0.22832434541740176 0.2167630649606005],
   :xe 0.05,
   :nature-exponents [0.14515223083054263],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.12101441362418973],
   :industry 1,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 1,
   :labor-quantities [0],
   :production-inputs [[1 2 3 4] [1 2] [2]],
   :input-exponents
   [0.09425099613766391
    0.08256457349186111
    0.12239607981358877
    0.0935888717651582],
   :xe 0.05,
   :nature-exponents [0.10859663283579876 0.1219184958081937],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.21548983158417984],
   :industry 1,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 1,
   :labor-quantities [0],
   :production-inputs [[2] [1 2] [1]],
   :input-exponents [0.23974009688140924],
   :xe 0.05,
   :nature-exponents [0.16952522215028448 0.23260382177545277],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.14278914427145886],
   :industry 1,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 1,
   :labor-quantities [0],
   :production-inputs [[1 2 3] [1 2] [2]],
   :input-exponents
   [0.15464433226553748 0.08987537782036804 0.1218024580924961],
   :xe 0.05,
   :nature-exponents [0.1620837429804888 0.10100854685792315],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.17376570897064253 0.13584469149572834],
   :industry 1,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 2,
   :labor-quantities [0],
   :production-inputs [[1 4] [2] [1 2]],
   :input-exponents [0.15490949093371306 0.1038030964959055],
   :xe 0.05,
   :nature-exponents [0.17302062833014492],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.140387891871783],
   :industry 1,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 2,
   :labor-quantities [0],
   :production-inputs [[1 2 3 4] [2] [2]],
   :input-exponents
   [0.13360896028214486
    0.08570026952978663
    0.13105768596499806
    0.14387323734075785],
   :xe 0.05,
   :nature-exponents [0.10270078843186418],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.1294396861864246],
   :industry 1,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 2,
   :labor-quantities [0],
   :production-inputs [[1 4] [1 2] [2]],
   :input-exponents [0.11763990698728213 0.15448519663709948],
   :xe 0.05,
   :nature-exponents [0.18140416165311624 0.10404406832077652],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.11479163164253722 0.19594816652167948],
   :industry 1,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 2,
   :labor-quantities [0],
   :production-inputs [[1] [1 2] [1 2]],
   :input-exponents [0.15829036558063883],
   :xe 0.05,
   :nature-exponents [0.17121886169994271 0.10032456238871368],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.33224508457668656],
   :industry 1,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 2,
   :labor-quantities [0],
   :production-inputs [[3] [2] [2]],
   :input-exponents [0.18346784433212499],
   :xe 0.05,
   :nature-exponents [0.20035178260448605],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.08804273578925254 0.11005897024697678],
   :industry 1,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 3,
   :labor-quantities [0],
   :production-inputs [[1 2 4] [1 2] [1 2]],
   :input-exponents
   [0.07182806077131125 0.07771883580040563 0.09776813662772448],
   :xe 0.05,
   :nature-exponents [0.08189740886893303 0.09243665034493723],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.1185670497308603 0.12799559461626653],
   :industry 1,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 3,
   :labor-quantities [0],
   :production-inputs [[1 2 3] [1] [1 2]],
   :input-exponents
   [0.1278876032921873 0.15254446994171716 0.16549094170619874],
   :xe 0.05,
   :nature-exponents [0.12348008118917418],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.14749719473350698 0.1327976303835542],
   :industry 1,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 3,
   :labor-quantities [0],
   :production-inputs [[1 3] [1 2] [1 2]],
   :input-exponents [0.13570440248988844 0.1035996134329282],
   :xe 0.05,
   :nature-exponents [0.12627497665885745 0.09227969572236867],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.07019218900000752 0.09694424096651796],
   :industry 1,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 3,
   :labor-quantities [0],
   :production-inputs [[1 2 3 4] [1 2] [1 2]],
   :input-exponents
   [0.12379798536911309
    0.06886967594491841
    0.11429606131116044
    0.09374000506451696],
   :xe 0.05,
   :nature-exponents [0.08710368540648937 0.06685146594506056],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.1759958008258 0.1720860890739468],
   :industry 1,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 3,
   :labor-quantities [0],
   :production-inputs [[4] [1 2] [1 2]],
   :input-exponents [0.142007722598426],
   :xe 0.05,
   :nature-exponents [0.15774887367648507 0.1829999903319246],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.14719460805453932],
   :industry 1,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 4,
   :labor-quantities [0],
   :production-inputs [[4] [1 2] [2]],
   :input-exponents [0.2395828141336814],
   :xe 0.05,
   :nature-exponents [0.1714933156643409 0.14720771464213972],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.14982673948590025 0.16339648523254005],
   :industry 1,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 4,
   :labor-quantities [0],
   :production-inputs [[1 2] [1 2] [1 2]],
   :input-exponents [0.10166170992465472 0.10560725281899085],
   :xe 0.05,
   :nature-exponents [0.0933568750293573 0.1454146722099684],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.16957742793444447 0.17515816537481202],
   :industry 1,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 4,
   :labor-quantities [0],
   :production-inputs [[2 4] [2] [1 2]],
   :input-exponents [0.14244036903725799 0.11245005063718244],
   :xe 0.05,
   :nature-exponents [0.17115648478053236],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.1338354300208197 0.13075477298425126],
   :industry 1,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 4,
   :labor-quantities [0],
   :production-inputs [[1 2 3 4] [2] [1 2]],
   :input-exponents
   [0.07772416355030716
    0.13956233249888986
    0.14255003591649815
    0.11915825439971342],
   :xe 0.05,
   :nature-exponents [0.12843080386151295],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.12470066825920462 0.1446566073007033],
   :industry 1,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 4,
   :labor-quantities [0],
   :production-inputs [[1 2 3] [2] [1 2]],
   :input-exponents
   [0.16163826869606554 0.14176719902329535 0.12661535528949125],
   :xe 0.05,
   :nature-exponents [0.14083269557080136],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.10379315311075757],
   :industry 0,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 1,
   :labor-quantities [0],
   :production-inputs [[1 2 3 4] [1 2] [2]],
   :input-exponents
   [0.09883079391215083
    0.07805740959968499
    0.09328249160563942
    0.11610494757040998],
   :xe 0.05,
   :nature-exponents [0.09706336322842153 0.12040225148565172],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.18812613084550356],
   :industry 0,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 1,
   :labor-quantities [0],
   :production-inputs [[1 4] [1] [2]],
   :input-exponents [0.18606632771310427 0.1662796786053254],
   :xe 0.05,
   :nature-exponents [0.1255500963073684],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.08952510192995305 0.10466769174974491],
   :industry 0,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 1,
   :labor-quantities [0],
   :production-inputs [[1 3 4] [1 2] [1 2]],
   :input-exponents
   [0.13907765587556897 0.12298330797763954 0.0901970982238432],
   :xe 0.05,
   :nature-exponents [0.08663085969658613 0.14103409319642143],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.11533500298136609 0.10929503633332839],
   :industry 0,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 1,
   :labor-quantities [0],
   :production-inputs [[1 2 3] [1 2] [1 2]],
   :input-exponents
   [0.12491988477317909 0.10076216445391734 0.0806576897886099],
   :xe 0.05,
   :nature-exponents [0.09841318501615128 0.14268839740269737],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.09413356608666018],
   :industry 0,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 1,
   :labor-quantities [0],
   :production-inputs [[1 2 3 4] [1] [2]],
   :input-exponents
   [0.10575126512999829
    0.1402753192372689
    0.09898280443304355
    0.08516703699648645],
   :xe 0.05,
   :nature-exponents [0.11656045415731942],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.13295490187800513 0.11286845069705319],
   :industry 0,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 2,
   :labor-quantities [0],
   :production-inputs [[4] [1 2] [1 2]],
   :input-exponents [0.15517238225566357],
   :xe 0.05,
   :nature-exponents [0.11300047105680663 0.11515724959518468],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.1361248040587222 0.159368806987019],
   :industry 0,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 2,
   :labor-quantities [0],
   :production-inputs [[1] [1 2] [1 2]],
   :input-exponents [0.1450812041388771],
   :xe 0.05,
   :nature-exponents [0.14515102034928756 0.11664175336028441],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.1328234644912116 0.1763728473480707],
   :industry 0,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 2,
   :labor-quantities [0],
   :production-inputs [[1] [1 2] [1 2]],
   :input-exponents [0.10095270677580886],
   :xe 0.05,
   :nature-exponents [0.18224775432107276 0.1851261528966904],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.19036399972315882],
   :industry 0,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 2,
   :labor-quantities [0],
   :production-inputs [[2 3] [1 2] [2]],
   :input-exponents [0.16804759699348168 0.14051492864699816],
   :xe 0.05,
   :nature-exponents [0.1589878273183085 0.1506687197796688],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.16070477187806223 0.13096647759796837],
   :industry 0,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 2,
   :labor-quantities [0],
   :production-inputs [[4] [1 2] [1 2]],
   :input-exponents [0.12278723463949545],
   :xe 0.05,
   :nature-exponents [0.126179167752212 0.15478297313506365],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.15348449481659737 0.12034683500786156],
   :industry 0,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 3,
   :labor-quantities [0],
   :production-inputs [[2 3 4] [1] [1 2]],
   :input-exponents
   [0.12743966022787545 0.16591463391114658 0.11344724958227709],
   :xe 0.05,
   :nature-exponents [0.14125392741020576],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.1682727691868871],
   :industry 0,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 3,
   :labor-quantities [0],
   :production-inputs [[1] [1 2] [2]],
   :input-exponents [0.13896517817518583],
   :xe 0.05,
   :nature-exponents [0.23540963346144833 0.2341612155245729],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.07109801778349065 0.09303139788615516],
   :industry 0,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 3,
   :labor-quantities [0],
   :production-inputs [[1 2 3 4] [1 2] [1 2]],
   :input-exponents
   [0.10213967735030657
    0.12281491855327098
    0.11165353775920088
    0.11283923349765998],
   :xe 0.05,
   :nature-exponents [0.09878107519989485 0.08157415709112534],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.15767599850075675],
   :industry 0,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 3,
   :labor-quantities [0],
   :production-inputs [[1 2 3 4] [2] [1]],
   :input-exponents
   [0.11791313614089047
    0.1255154631814318
    0.1616950396773028
    0.15324783470774256],
   :xe 0.05,
   :nature-exponents [0.1571430931072058],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.11312543281902937],
   :industry 0,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 3,
   :labor-quantities [0],
   :production-inputs [[1 2 3 4] [1 2] [1]],
   :input-exponents
   [0.07573109765407085
    0.122237216363489
    0.13459988046855278
    0.13296971263862],
   :xe 0.05,
   :nature-exponents [0.12795741593517002 0.07377682048167802],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.11407437995262011 0.11363160974132672],
   :industry 0,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 4,
   :labor-quantities [0],
   :production-inputs [[1 2 3 4] [1 2] [1 2]],
   :input-exponents
   [0.07101790029146407
    0.11669675555504241
    0.06694548701422591
    0.11468742723956696],
   :xe 0.05,
   :nature-exponents [0.09791228289771314 0.0754861650478856],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.1810049056049483],
   :industry 0,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 4,
   :labor-quantities [0],
   :production-inputs [[3] [1] [2]],
   :input-exponents [0.2728437340250499],
   :xe 0.05,
   :nature-exponents [0.1935415008146957],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.11741362624544958],
   :industry 0,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 4,
   :labor-quantities [0],
   :production-inputs [[1 3] [1 2] [1]],
   :input-exponents [0.127468479174634 0.1754719935579996],
   :xe 0.05,
   :nature-exponents [0.15794057279183404 0.1567470375608099],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.06724981438709571 0.07710899910218753],
   :industry 0,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 4,
   :labor-quantities [0],
   :production-inputs [[1 2 3 4] [1 2] [1 2]],
   :input-exponents
   [0.11741391387744358
    0.1119583953258858
    0.0976763625305496
    0.11325788673105222],
   :xe 0.05,
   :nature-exponents [0.07775806084715882 0.09280038015702577],
   :a 0.25}
  {:effort 0.5,
   :cq 0.25,
   :ce 1,
   :labor-exponents [0.20210503291291582 0.21575306187450086],
   :industry 0,
   :output 0,
   :s 1,
   :du 7,
   :c 0.05,
   :product 4,
   :labor-quantities [0],
   :production-inputs [[4] [2] [1 2]],
   :input-exponents [0.13033824220102636],
   :xe 0.05,
   :nature-exponents [0.18743561413171494],
   :a 0.25}))
