(ns pequod-cljs.ex002)

(def ccs
 '({:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.1269113594105148
     0.15751165045120752
     0.12178798667648469
     0.11500495056982693],
    :final-demands [0 0 0 0 0],
    :cy 7.982963417787742,
    :public-good-exponents [0.12107716832042702 0.1437019909692136],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.16659902496717766
     0.1435017891516326
     0.12045464021992136
     0.16146176965157819],
    :final-demands [0 0 0 0 0],
    :cy 10.254734426617272,
    :public-good-exponents [0.156314701869953 0.10327268826297485],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.09077729110238737
     0.15812014752871556
     0.14705422227703557
     0.14089679348144285],
    :final-demands [0 0 0 0 0],
    :cy 12.822222985395111,
    :public-good-exponents [0.13335506063405306 0.09329406349199779],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.13746849358528163
     0.16243217156030984
     0.14752965751139058
     0.12929775568739849],
    :final-demands [0 0 0 0 0],
    :cy 7.144245204601812,
    :public-good-exponents [0.1447878644154545 0.13508950263983668],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.15594214770494141
     0.14352548947407248
     0.12673238688412758
     0.15795241147246425],
    :final-demands [0 0 0 0 0],
    :cy 8.082325567985034,
    :public-good-exponents [0.10286122988070694 0.09701494964447964],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.10150700090258856
     0.08589672809442458
     0.11095720860818495
     0.1289398271756806],
    :final-demands [0 0 0 0 0],
    :cy 9.791384609517642,
    :public-good-exponents [0.1220146506808066 0.09687685207927364],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.1285133084865294
     0.12532802777402846
     0.16302317478597117
     0.08989981735736394],
    :final-demands [0 0 0 0 0],
    :cy 12.24034709126251,
    :public-good-exponents [0.12841896931914204 0.10522766607536838],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.11998406186696292
     0.14653075433329132
     0.1637693652447612
     0.12559603377668305],
    :final-demands [0 0 0 0 0],
    :cy 11.673155442628598,
    :public-good-exponents [0.09809981842082202 0.08637792763335861],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.13220012817894622
     0.1103600772350653
     0.08940584587054155
     0.08818305042989419],
    :final-demands [0 0 0 0 0],
    :cy 12.83451474641051,
    :public-good-exponents [0.1327789938830537 0.16400875179867325],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.14684990311600793
     0.16297664728223732
     0.10481921765780446
     0.14795207734543994],
    :final-demands [0 0 0 0 0],
    :cy 11.03281422754685,
    :public-good-exponents [0.08689285418426157 0.10204320624912942],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.11428989232852357
     0.13940498108265897
     0.08355585130756547
     0.16653017843189563],
    :final-demands [0 0 0 0 0],
    :cy 6.399957254605455,
    :public-good-exponents [0.11347388823497259 0.12274694464279462],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.0905674240473345
     0.14188088620188277
     0.11557884761464524
     0.08345552708519066],
    :final-demands [0 0 0 0 0],
    :cy 10.879006025072613,
    :public-good-exponents [0.13297392218410775 0.13978494907192213],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.16237587312232657
     0.11306195296443347
     0.1357049906659779
     0.09776935935264075],
    :final-demands [0 0 0 0 0],
    :cy 9.447818324762398,
    :public-good-exponents [0.1050394232599614 0.10060486298773572],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.10187378359853326
     0.1359982640513342
     0.1556295735953524
     0.08381675691278168],
    :final-demands [0 0 0 0 0],
    :cy 7.280096017722228,
    :public-good-exponents [0.157392509481538 0.08598266968746565],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.10616628797054989
     0.11605114809225867
     0.09721261640710639
     0.12250496994862868],
    :final-demands [0 0 0 0 0],
    :cy 11.326252206911512,
    :public-good-exponents [0.11031715981384155 0.1457479870734512],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.1260407329963225
     0.10342186856875757
     0.11604661342961378
     0.1627529433741732],
    :final-demands [0 0 0 0 0],
    :cy 6.822182151548628,
    :public-good-exponents [0.09725577090890804 0.16524614027720086],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.1637781838438908
     0.09329528009725153
     0.15895895492264878
     0.1415780729024642],
    :final-demands [0 0 0 0 0],
    :cy 9.247847674033235,
    :public-good-exponents [0.13730672590321402 0.15060199651946798],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.162263481030487
     0.08467387380826773
     0.12135652673827983
     0.10220683325939557],
    :final-demands [0 0 0 0 0],
    :cy 12.091525904455718,
    :public-good-exponents [0.13841905968034027 0.15915569471838298],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.08962867403584947
     0.1241274378628368
     0.15633180680938497
     0.16269819864071883],
    :final-demands [0 0 0 0 0],
    :cy 6.619249283908533,
    :public-good-exponents [0.13123980349053985 0.124116909033051],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.10363757420259388
     0.0921666233137004
     0.1394305534352135
     0.16134445474142817],
    :final-demands [0 0 0 0 0],
    :cy 14.44595616448812,
    :public-good-exponents [0.15353246010198318 0.09403679844800028],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.08807367651607749
     0.12970234189217583
     0.14005359465282194
     0.13604162760919924],
    :final-demands [0 0 0 0 0],
    :cy 10.980232842384655,
    :public-good-exponents [0.15600131087557856 0.10710857756148605],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.11451438023673918
     0.10061316563250014
     0.08785458919771383
     0.0911150494293683],
    :final-demands [0 0 0 0 0],
    :cy 11.531141759628252,
    :public-good-exponents [0.1378808316858413 0.11879577093064031],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.10828836350591042
     0.09740434744215865
     0.14699956003761466
     0.14912443647741264],
    :final-demands [0 0 0 0 0],
    :cy 14.25063540691312,
    :public-good-exponents [0.15480787347244046 0.1494260053422602],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.15395529103250807
     0.08585044747126361
     0.1344696058238265
     0.10002994333535581],
    :final-demands [0 0 0 0 0],
    :cy 9.961873386585918,
    :public-good-exponents [0.1098555768816132 0.15490241506414792],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.14229257688399305
     0.15272196573916794
     0.16455058742006956
     0.1492340936570114],
    :final-demands [0 0 0 0 0],
    :cy 9.728146535066909,
    :public-good-exponents [0.14136543936735999 0.14686663789194535],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.11849433077027657
     0.09087001040863765
     0.13875175996952427
     0.10302041342284604],
    :final-demands [0 0 0 0 0],
    :cy 13.170341429501647,
    :public-good-exponents [0.0914129414792084 0.11415745344026516],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.11687139702433007
     0.11953205484764332
     0.11041388221559154
     0.10386318727197215],
    :final-demands [0 0 0 0 0],
    :cy 6.996183915077637,
    :public-good-exponents [0.11204403195895374 0.11320496052680826],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.13046126423188897
     0.15587853523309836
     0.15721084807960722
     0.14948457457692915],
    :final-demands [0 0 0 0 0],
    :cy 12.576968120389317,
    :public-good-exponents [0.0934366996315892 0.1319469941427315],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.0929136106387154
     0.1485418468723897
     0.14835481836497066
     0.12218637042176322],
    :final-demands [0 0 0 0 0],
    :cy 13.941456872512678,
    :public-good-exponents [0.12518651764087585 0.16388470481622391],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.1519032980349378
     0.12229641644672502
     0.12643864090665063
     0.1502613884209652],
    :final-demands [0 0 0 0 0],
    :cy 9.278114175666387,
    :public-good-exponents [0.16632733112213438 0.11606288753198485],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.1088244519825766
     0.14483913183868075
     0.11415112652423207
     0.1186078340366307],
    :final-demands [0 0 0 0 0],
    :cy 7.782275333137049,
    :public-good-exponents [0.11038310496774671 0.11618806634429832],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.14456183757939323
     0.11872398645473457
     0.1644424434591443
     0.1550552393170444],
    :final-demands [0 0 0 0 0],
    :cy 13.291779747759877,
    :public-good-exponents [0.11754481357230442 0.08893811842008673],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.15038060444166815
     0.13019739266575087
     0.09215866395103531
     0.11393080325097897],
    :final-demands [0 0 0 0 0],
    :cy 14.942464539752432,
    :public-good-exponents [0.09404822993863314 0.08473796233880096],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.10383117795927023
     0.11248722664779764
     0.1074259472975257
     0.1255045433434766],
    :final-demands [0 0 0 0 0],
    :cy 14.839506212942432,
    :public-good-exponents [0.12826213189166658 0.14683963127340932],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.11651630919584981
     0.10293502310037782
     0.12713280636019192
     0.1256095314706926],
    :final-demands [0 0 0 0 0],
    :cy 10.827591520490307,
    :public-good-exponents [0.10853381778032103 0.125910386957098],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.14826667001216431
     0.11245739039990986
     0.11606560709281832
     0.15081743427178718],
    :final-demands [0 0 0 0 0],
    :cy 10.061622252962103,
    :public-good-exponents [0.11079659775859488 0.1206544954065055],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.13200558749441466
     0.09206207680517868
     0.15762935844793377
     0.15854982183248462],
    :final-demands [0 0 0 0 0],
    :cy 14.860101445535673,
    :public-good-exponents [0.1333527239747096 0.08873057868191096],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.144669517074349
     0.0872430663664076
     0.13211099478757587
     0.13881001007748867],
    :final-demands [0 0 0 0 0],
    :cy 11.214788154182926,
    :public-good-exponents [0.10499641206991984 0.12265735004306418],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.141730879604632
     0.14702177390306384
     0.12977621351414925
     0.10010222319438256],
    :final-demands [0 0 0 0 0],
    :cy 6.282345822977199,
    :public-good-exponents [0.15519993571134572 0.152893666374047],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.16440119871099312
     0.1390141516614002
     0.10187757548902661
     0.0955153596774597],
    :final-demands [0 0 0 0 0],
    :cy 6.645780648323084,
    :public-good-exponents [0.09855468113215962 0.13859763258178104],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.0908779430120935
     0.12043593152903331
     0.16177245911136107
     0.1448502803732246],
    :final-demands [0 0 0 0 0],
    :cy 11.25012604925207,
    :public-good-exponents [0.11879261488364465 0.11518084939874185],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.1162507668637065
     0.09435202013075458
     0.12399701245906837
     0.16313690766136596],
    :final-demands [0 0 0 0 0],
    :cy 9.393394910647306,
    :public-good-exponents [0.09278501612320941 0.09524813003588198],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.11459380715055285
     0.09431336639202118
     0.10756033075384075
     0.14783987550143615],
    :final-demands [0 0 0 0 0],
    :cy 10.107452756932759,
    :public-good-exponents [0.11849742004892155 0.131759754094169],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.1504690129748617
     0.11679335256922746
     0.08527192024113726
     0.10996439197313371],
    :final-demands [0 0 0 0 0],
    :cy 12.97028924797537,
    :public-good-exponents [0.11490217078084769 0.1424811454578471],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.1271263390661465
     0.14096285666622327
     0.1367670413612055
     0.09486128521767025],
    :final-demands [0 0 0 0 0],
    :cy 6.683270568914388,
    :public-good-exponents [0.10578298752449097 0.10071802676349723],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.11260228791856902
     0.10575944698311225
     0.14664153539142
     0.1445361910060354],
    :final-demands [0 0 0 0 0],
    :cy 12.907062521366411,
    :public-good-exponents [0.10468604586916752 0.15403489313685742],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.10024974202582303
     0.12201632743132645
     0.115382434110016
     0.1664844127433284],
    :final-demands [0 0 0 0 0],
    :cy 12.19482429982073,
    :public-good-exponents [0.1441835725126848 0.08626884884283831],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.13664417831008935
     0.1562321216933486
     0.12662632665151707
     0.1648473227441395],
    :final-demands [0 0 0 0 0],
    :cy 10.79967060356832,
    :public-good-exponents [0.16397752794275522 0.12039876907072464],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.16566988698050067
     0.10986069584993155
     0.15082671460676705
     0.15949201933753182],
    :final-demands [0 0 0 0 0],
    :cy 11.326451470774119,
    :public-good-exponents [0.11692405353597768 0.13500384513441438],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.15811193357192974
     0.10486006455156624
     0.10473842503311631
     0.16174519218910355],
    :final-demands [0 0 0 0 0],
    :cy 11.545685192526413,
    :public-good-exponents [0.14103389590598273 0.09612794446220038],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.09117837505666615
     0.10727645091701976
     0.09495381809026232
     0.10625278724151069],
    :final-demands [0 0 0 0 0],
    :cy 14.207523343213088,
    :public-good-exponents [0.13948923359712717 0.10983519689832706],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.11764762537747382
     0.10311206467449181
     0.11264073859868272
     0.12625042785555493],
    :final-demands [0 0 0 0 0],
    :cy 14.951492121841016,
    :public-good-exponents [0.14133214196823435 0.15945566730515398],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.09144721913555996
     0.13376377566216774
     0.13129486389545886
     0.15172052886237097],
    :final-demands [0 0 0 0 0],
    :cy 14.004077671884316,
    :public-good-exponents [0.1161882526156442 0.15371163597370527],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.10287394491062901
     0.12604025079849718
     0.1087620833477867
     0.14576071224337728],
    :final-demands [0 0 0 0 0],
    :cy 13.067266089762654,
    :public-good-exponents [0.08612021853353533 0.10714768322183069],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.08900411860184515
     0.1343434949516288
     0.1614186237811942
     0.12679738512917177],
    :final-demands [0 0 0 0 0],
    :cy 6.314793932276148,
    :public-good-exponents [0.13541607344562062 0.12219070984864473],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.11909835058609043
     0.1405294494094348
     0.1579441364520992
     0.14709603619123562],
    :final-demands [0 0 0 0 0],
    :cy 11.194821612744633,
    :public-good-exponents [0.15274700811331665 0.11826021890876437],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.11236016947262845
     0.11568922916797933
     0.14089930181526505
     0.16628151219474152],
    :final-demands [0 0 0 0 0],
    :cy 12.407148440483299,
    :public-good-exponents [0.0862110605016442 0.15291169673140032],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.12785181007319638
     0.12305498283721553
     0.10052243889693124
     0.09322603224163856],
    :final-demands [0 0 0 0 0],
    :cy 7.109944518453924,
    :public-good-exponents [0.11895057206841281 0.12039215231430231],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.12635026274555605
     0.13694852825617282
     0.1483777867173557
     0.15375550411326716],
    :final-demands [0 0 0 0 0],
    :cy 7.912023107687144,
    :public-good-exponents [0.10049448915780573 0.10188064610544645],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.15132920897434143
     0.15069850394927176
     0.12857270022413564
     0.09583458838262499],
    :final-demands [0 0 0 0 0],
    :cy 10.349642924336372,
    :public-good-exponents [0.1296148634424087 0.1463486088151028],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.15514600624615488
     0.15557909826512928
     0.0985374853325447
     0.12331389250787878],
    :final-demands [0 0 0 0 0],
    :cy 11.01724852477696,
    :public-good-exponents [0.12514619180079725 0.12588982701908108],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.08525835146542224
     0.1080092918650378
     0.1397949557559364
     0.12812148044108268],
    :final-demands [0 0 0 0 0],
    :cy 13.499422556999646,
    :public-good-exponents [0.1029789801982445 0.15215769629697196],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.15671359479487912
     0.14852731872057262
     0.14589404019155677
     0.1464277977191067],
    :final-demands [0 0 0 0 0],
    :cy 10.54948268508921,
    :public-good-exponents [0.1631935661416216 0.12651834584041036],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.15761870308372194
     0.11205726064826771
     0.16343581229773654
     0.12269475927914983],
    :final-demands [0 0 0 0 0],
    :cy 13.94901620288963,
    :public-good-exponents [0.11989319411575726 0.1000188618689325],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.1567651905281331
     0.16637357203779646
     0.14047191951008645
     0.08848439006206664],
    :final-demands [0 0 0 0 0],
    :cy 9.93071447069207,
    :public-good-exponents [0.10202307289881407 0.15853337609229884],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.1255461804426761
     0.10696221335366038
     0.1565682203270557
     0.10096500068611189],
    :final-demands [0 0 0 0 0],
    :cy 6.099224197125362,
    :public-good-exponents [0.10665299712908306 0.16370664733135587],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.09848972967162954
     0.11412425425223573
     0.08384355513583397
     0.1309654406637208],
    :final-demands [0 0 0 0 0],
    :cy 8.646235731095791,
    :public-good-exponents [0.16509970079439681 0.16153131628029765],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.1426476053832074
     0.12884139986752904
     0.13962914383807576
     0.1617882492419228],
    :final-demands [0 0 0 0 0],
    :cy 14.139245304743463,
    :public-good-exponents [0.1317618384891412 0.16585104643786697],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.12699383836841963
     0.08396971428100326
     0.10305299354173371
     0.1303877657375944],
    :final-demands [0 0 0 0 0],
    :cy 14.105157100267396,
    :public-good-exponents [0.15441753536830904 0.1227557191324212],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.09829031653116116
     0.12148175969260527
     0.14605907214806807
     0.1635084404296188],
    :final-demands [0 0 0 0 0],
    :cy 8.274070625638728,
    :public-good-exponents [0.12009469114416843 0.12545727074714358],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.09149625347942389
     0.09224170441361566
     0.10316541692570931
     0.14792823171873126],
    :final-demands [0 0 0 0 0],
    :cy 7.136882552313658,
    :public-good-exponents [0.09596812096609642 0.12349886388507275],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.09740910722948384
     0.09271661574078469
     0.15709279683448907
     0.1544264230087622],
    :final-demands [0 0 0 0 0],
    :cy 7.275543322924614,
    :public-good-exponents [0.09014292063406223 0.09571973377186276],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.16007479014054926
     0.15171500155743384
     0.1407199607942839
     0.11087139858199321],
    :final-demands [0 0 0 0 0],
    :cy 8.606191309268908,
    :public-good-exponents [0.09618218853109173 0.09566252864985954],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.14336261026168037
     0.13451910144015733
     0.14114849642024996
     0.08642380422173898],
    :final-demands [0 0 0 0 0],
    :cy 10.928251281724709,
    :public-good-exponents [0.08996496401875528 0.12311512206169534],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.12728665809362655
     0.10922585180384081
     0.10347481370827981
     0.1257874202477185],
    :final-demands [0 0 0 0 0],
    :cy 7.988859206338251,
    :public-good-exponents [0.08813498533656533 0.1394450654460728],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.11878371518665284
     0.12588106513548478
     0.14098027427651103
     0.1384622925876085],
    :final-demands [0 0 0 0 0],
    :cy 7.921658298111181,
    :public-good-exponents [0.08416083625138929 0.13036319064306373],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.1645084275207354
     0.13060908807947488
     0.10639340944748003
     0.16534141780933936],
    :final-demands [0 0 0 0 0],
    :cy 13.20663685407047,
    :public-good-exponents [0.12598102262843436 0.08862475185396557],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.12781127203342807
     0.08707244645583084
     0.08929057826000306
     0.1643779124364648],
    :final-demands [0 0 0 0 0],
    :cy 7.9412295795152215,
    :public-good-exponents [0.09450981552376872 0.1320046439456244],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.14822328751656957
     0.08966871256941278
     0.12381634090401697
     0.15622114203563942],
    :final-demands [0 0 0 0 0],
    :cy 13.536792833273054,
    :public-good-exponents [0.10489039465775038 0.12861044221681317],
    :public-good-demands [0],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.08886825671462939
     0.10226616848442781
     0.15953439665571095
     0.1636569741391834],
    :final-demands [0 0 0 0 0],
    :cy 9.68165467430492,
    :public-good-exponents [0.13643573163079858 0.10922814265054098],
    :public-good-demands [0],
    :income 5000}))

(def wcs
'({:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.3270319214702696 0.3460151243624603],
  :industry 2,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[1 2 3] [1 2] [1 2]],
  :input-exponents
  [0.14821730053706317 0.1874517845099104 0.18057576669420333],
  :xe 0.05,
  :nature-exponents [0.3269862772597397 0.32368539586782974],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.5208039677223916],
  :industry 2,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[2 3 4] [1 2] [1]],
  :input-exponents
  [0.13593589351027205 0.13159326588910092 0.10577722640233898],
  :xe 0.05,
  :nature-exponents [0.2929520090075665 0.2480311433635991],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.3883670199981231],
  :industry 2,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[2 4] [1 2] [1]],
  :input-exponents [0.24833998855366618 0.29245306716006614],
  :xe 0.05,
  :nature-exponents [0.24974313027741077 0.25644789335416546],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.25931509586570095 0.31133621703629977],
  :industry 2,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [1 2] [1 2]],
  :input-exponents
  [0.12042162014286366
   0.13402673057760542
   0.14545018700281193
   0.10125555554591302],
  :xe 0.05,
  :nature-exponents [0.34743163614416717 0.29261572463007557],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.30999460006545193 0.31175392518387157],
  :industry 2,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [1 2] [1 2]],
  :input-exponents
  [0.09525948443169174
   0.10253678295483513
   0.0797930693424053
   0.12159454784581666],
  :xe 0.05,
  :nature-exponents [0.24163116915353955 0.2674338950633495],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.24126455802737304 0.2735540266376779],
  :industry 2,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[1 4] [2] [1 2]],
  :input-exponents [0.21553726499573 0.15796916492366772],
  :xe 0.05,
  :nature-exponents [0.6344866848714126],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.5897619392575582],
  :industry 2,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [1] [1]],
  :input-exponents
  [0.08158935879989937
   0.09965523893801487
   0.10894690245380936
   0.09891517937582493],
  :xe 0.05,
  :nature-exponents [0.4351002260251111],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.48752912299310047],
  :industry 2,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[1 4] [1] [1]],
  :input-exponents [0.20649254335302236 0.27442880244659124],
  :xe 0.05,
  :nature-exponents [0.6023188970529656],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.36513680522997505],
  :industry 2,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [2] [1]],
  :input-exponents
  [0.11105193450547912
   0.07664316526270634
   0.13065518729662845
   0.11857935166110284],
  :xe 0.05,
  :nature-exponents [0.4697586251908974],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.6140291946899707],
  :industry 2,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[3 4] [2] [2]],
  :input-exponents [0.2534139969789819 0.19948337083681458],
  :xe 0.05,
  :nature-exponents [0.46493859131680765],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.5431039600767901],
  :industry 2,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[2 3 4] [1] [2]],
  :input-exponents
  [0.14217376362758102 0.16482586514140252 0.10664170220866885],
  :xe 0.05,
  :nature-exponents [0.5781917618272849],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.2945251264923856 0.21832702189779737],
  :industry 2,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[1 3 4] [2] [1 2]],
  :input-exponents
  [0.12268292722091433 0.13955876627805736 0.16406062900062435],
  :xe 0.05,
  :nature-exponents [0.5559121992880598],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.23123517789186004 0.3428477350518039],
  :industry 2,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[1 2 4] [1 2] [1 2]],
  :input-exponents
  [0.13711389392257756 0.19607861260977508 0.1862908526944994],
  :xe 0.05,
  :nature-exponents [0.27760178442931965 0.21773900036983207],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.2939053953237945 0.3291277202783611],
  :industry 2,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [2] [1 2]],
  :input-exponents
  [0.1482696622559239
   0.11109018573502338
   0.1424381725706107
   0.12419879968927311],
  :xe 0.05,
  :nature-exponents [0.4022122203176453],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.5312349358419934],
  :industry 2,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[2 3] [1 2] [2]],
  :input-exponents [0.2046986868623411 0.20440882033609645],
  :xe 0.05,
  :nature-exponents [0.2703550298079609 0.22688918479972175],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.3530626061222743],
  :industry 2,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[2] [2] [1]],
  :input-exponents [0.44415788685139757],
  :xe 0.05,
  :nature-exponents [0.41927946443357905],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.3129955833009872 0.26102819549437867],
  :industry 2,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [1] [1 2]],
  :input-exponents
  [0.1390715368347282
   0.09272685189823832
   0.09770531743085478
   0.12922016479131843],
  :xe 0.05,
  :nature-exponents [0.3779151217717275],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.27802439398083334 0.28048918361172864],
  :industry 2,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[1] [1 2] [1 2]],
  :input-exponents [0.3310639792722753],
  :xe 0.05,
  :nature-exponents [0.2118482179459203 0.23849957805203686],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.23583532969436227 0.26243844918484666],
  :industry 2,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[1 3 4] [2] [1 2]],
  :input-exponents
  [0.14161848560933496 0.13859610543488843 0.13790062601183095],
  :xe 0.05,
  :nature-exponents [0.36537789923404773],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.44215732260495755],
  :industry 2,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[2 4] [1 2] [1]],
  :input-exponents [0.22040310049659456 0.2812809563603459],
  :xe 0.05,
  :nature-exponents [0.2835157106615356 0.20799874595941206],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.5046408274644267],
  :industry 1,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[3 4] [1 2] [1]],
  :input-exponents [0.2746891377323011 0.20668177098438292],
  :xe 0.05,
  :nature-exponents [0.3034470372044346 0.21533759410334066],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.33027222537692513 0.27724937415857753],
  :industry 1,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [1] [1 2]],
  :input-exponents
  [0.08302140558344603
   0.11462530430013576
   0.08500731062761212
   0.12292300602453457],
  :xe 0.05,
  :nature-exponents [0.514475279423712],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.30385494858307277 0.24059852715176158],
  :industry 1,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[1 2] [1 2] [1 2]],
  :input-exponents [0.1539126986663957 0.2297784523680697],
  :xe 0.05,
  :nature-exponents [0.26222284238701554 0.25727076951801753],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.23523683101193144 0.2738167725672193],
  :industry 1,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[1] [1] [1 2]],
  :input-exponents [0.5627988240802855],
  :xe 0.05,
  :nature-exponents [0.5639594583994832],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.38610198732281475],
  :industry 1,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[1 2 3] [2] [1]],
  :input-exponents
  [0.19081021259951914 0.11737962510660062 0.18584347502296106],
  :xe 0.05,
  :nature-exponents [0.5749175624974917],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.5516012987838376],
  :industry 1,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 2,
  :labor-quantities [0],
  :production-inputs [[1 2 4] [1 2] [2]],
  :input-exponents
  [0.14169715391333754 0.17758559869742482 0.10501949566759619],
  :xe 0.05,
  :nature-exponents [0.25325239554138707 0.257054995592931],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.36764572308408894],
  :industry 1,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 2,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [2] [2]],
  :input-exponents
  [0.11426845809310524
   0.1370504653253758
   0.11446121987712254
   0.08178483667846384],
  :xe 0.05,
  :nature-exponents [0.4443043465932538],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.2575956167797843 0.26281220877860023],
  :industry 1,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 2,
  :labor-quantities [0],
  :production-inputs [[1 2 4] [1 2] [1 2]],
  :input-exponents
  [0.13071288594232428 0.12673700023743198 0.13747192655305407],
  :xe 0.05,
  :nature-exponents [0.2629392153544596 0.33836814009128974],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.3478504958618691 0.23084714717173166],
  :industry 1,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 2,
  :labor-quantities [0],
  :production-inputs [[1 2] [1] [1 2]],
  :input-exponents [0.2605725429256399 0.15140053604194567],
  :xe 0.05,
  :nature-exponents [0.6059513374066412],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.38805131651638597],
  :industry 1,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 2,
  :labor-quantities [0],
  :production-inputs [[1 2 3] [1 2] [2]],
  :input-exponents
  [0.10792514356627389 0.19685710924841393 0.15174581251765318],
  :xe 0.05,
  :nature-exponents [0.34958844866266325 0.2728944145960748],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.5986945596488453],
  :industry 1,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 3,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [1 2] [2]],
  :input-exponents
  [0.08055611582077543
   0.11542803813768969
   0.09225252577259291
   0.10511064902558286],
  :xe 0.05,
  :nature-exponents [0.34954596497214774 0.307281901815848],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.5781165828415096],
  :industry 1,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 3,
  :labor-quantities [0],
  :production-inputs [[1 2 3] [1 2] [2]],
  :input-exponents
  [0.1644016217976556 0.12508931721988456 0.1505485609860635],
  :xe 0.05,
  :nature-exponents [0.3090285846114834 0.3403371977904529],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.28951685352855555 0.33263006982142596],
  :industry 1,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 3,
  :labor-quantities [0],
  :production-inputs [[1] [1 2] [1 2]],
  :input-exponents [0.42899036956842096],
  :xe 0.05,
  :nature-exponents [0.20935639713605775 0.20892902330406246],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.22796495439880854 0.27717317815674436],
  :industry 1,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 3,
  :labor-quantities [0],
  :production-inputs [[1 2 4] [1 2] [1 2]],
  :input-exponents
  [0.11294863158264787 0.14400396781561964 0.10938753465889677],
  :xe 0.05,
  :nature-exponents [0.2625145245273059 0.31854782115324065],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.30821345520895793 0.32787347814979984],
  :industry 1,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 3,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [1] [1 2]],
  :input-exponents
  [0.11760582891605223
   0.1415347672072781
   0.08585060915383053
   0.13698272227473746],
  :xe 0.05,
  :nature-exponents [0.36508856301102866],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.5234003063496622],
  :industry 1,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 4,
  :labor-quantities [0],
  :production-inputs [[2 4] [1 2] [1]],
  :input-exponents [0.22535357021907215 0.19971827007951104],
  :xe 0.05,
  :nature-exponents [0.28463053617326234 0.30199875089019496],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.4655585938214624],
  :industry 1,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 4,
  :labor-quantities [0],
  :production-inputs [[1 4] [1] [2]],
  :input-exponents [0.24572119785692198 0.24479179345610141],
  :xe 0.05,
  :nature-exponents [0.5362112329219842],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.5646941926910425],
  :industry 1,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 4,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [2] [1]],
  :input-exponents
  [0.08343475771880958
   0.11338840885614401
   0.10430503050066162
   0.14230164249988317],
  :xe 0.05,
  :nature-exponents [0.6090863750959816],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.6138860669457483],
  :industry 1,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 4,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [2] [2]],
  :input-exponents
  [0.11465017838589057
   0.13544070805217445
   0.13498130893556648
   0.10875906010663947],
  :xe 0.05,
  :nature-exponents [0.5718645638000543],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.4801571718541132],
  :industry 1,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 4,
  :labor-quantities [0],
  :production-inputs [[2 4] [1 2] [2]],
  :input-exponents [0.1664063719142328 0.23223436609581727],
  :xe 0.05,
  :nature-exponents [0.33010311515258156 0.26109345507414994],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.2331650749388792 0.3041609945248348],
  :industry 0,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [2] [1 2]],
  :input-exponents
  [0.11836285837493726
   0.10942342512120215
   0.10299505657647046
   0.10830110567446191],
  :xe 0.05,
  :nature-exponents [0.3946937112337554],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.5137354444861766],
  :industry 0,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [2] [2]],
  :input-exponents
  [0.08290564973047272
   0.1167686545371434
   0.10203446146462611
   0.13259170973883194],
  :xe 0.05,
  :nature-exponents [0.5210406003864466],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.4629409307225798],
  :industry 0,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [1 2] [2]],
  :input-exponents
  [0.1046663792344946
   0.10567492284426538
   0.13438531761598432
   0.14573059651654713],
  :xe 0.05,
  :nature-exponents [0.2703383598822782 0.29519152268700855],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.4608014284471243],
  :industry 0,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [1 2] [2]],
  :input-exponents
  [0.0883356372932687
   0.10757462857205806
   0.144418497343376
   0.08117768869437539],
  :xe 0.05,
  :nature-exponents [0.26649037065394243 0.3292142835913284],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.22939032479176455 0.20181961809920654],
  :industry 0,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [1 2] [1 2]],
  :input-exponents
  [0.12296173738364898
   0.1023488122539041
   0.12691701227223506
   0.10260826656611113],
  :xe 0.05,
  :nature-exponents [0.29653450659788433 0.29827954767692716],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.20334917362360186 0.3022520100898085],
  :industry 0,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 2,
  :labor-quantities [0],
  :production-inputs [[3] [1 2] [1 2]],
  :input-exponents [0.4664102770349902],
  :xe 0.05,
  :nature-exponents [0.26313020678015264 0.31514164064685857],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.4805888724930121],
  :industry 0,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 2,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [2] [1]],
  :input-exponents
  [0.09990907803208465
   0.11314945158723089
   0.09754665294326668
   0.11493993857742177],
  :xe 0.05,
  :nature-exponents [0.5210077210759625],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.3093877586192598 0.33883800163500905],
  :industry 0,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 2,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [2] [1 2]],
  :input-exponents
  [0.10766568486654743
   0.13594646563551768
   0.096053929063411
   0.0900239293455586],
  :xe 0.05,
  :nature-exponents [0.38382195414676856],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.4173771803344313],
  :industry 0,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 2,
  :labor-quantities [0],
  :production-inputs [[2 3 4] [1 2] [2]],
  :input-exponents
  [0.1936039435771454 0.16181432681699104 0.15129373599607066],
  :xe 0.05,
  :nature-exponents [0.27473170996409463 0.20069009550226727],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.20488101848056878 0.23793781672678893],
  :industry 0,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 2,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [1 2] [1 2]],
  :input-exponents
  [0.09051509319856008
   0.09176063372290484
   0.07763813674144636
   0.0880873519117371],
  :xe 0.05,
  :nature-exponents [0.309247719869938 0.28256337791955066],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.2949879653399158 0.24112831468318235],
  :industry 0,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 3,
  :labor-quantities [0],
  :production-inputs [[1 2] [1 2] [1 2]],
  :input-exponents [0.15213305680611114 0.24599009682907186],
  :xe 0.05,
  :nature-exponents [0.2930528116332849 0.2138757177656411],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.5407761153415188],
  :industry 0,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 3,
  :labor-quantities [0],
  :production-inputs [[1 3 4] [1 2] [1]],
  :input-exponents
  [0.11150178188919772 0.1028829670693278 0.12169763888904547],
  :xe 0.05,
  :nature-exponents [0.2830860451674866 0.25063076198785117],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.5763301041880873],
  :industry 0,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 3,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [2] [2]],
  :input-exponents
  [0.09088722558836426
   0.12493740240523141
   0.0822540533428192
   0.1436916290242539],
  :xe 0.05,
  :nature-exponents [0.377846610051858],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.26873373551695356 0.3257948433339632],
  :industry 0,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 3,
  :labor-quantities [0],
  :production-inputs [[3 4] [1 2] [1 2]],
  :input-exponents [0.22152935369315344 0.274000783409031],
  :xe 0.05,
  :nature-exponents [0.34883209865418463 0.3065412332557027],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.3194870686760427 0.24609955430775157],
  :industry 0,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 3,
  :labor-quantities [0],
  :production-inputs [[2] [1 2] [1 2]],
  :input-exponents [0.507645685760945],
  :xe 0.05,
  :nature-exponents [0.2663458649447013 0.26605001722174715],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.26166473875854135 0.21898019564968774],
  :industry 0,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 4,
  :labor-quantities [0],
  :production-inputs [[2 3] [1] [1 2]],
  :input-exponents [0.2602314741045275 0.29113247488973626],
  :xe 0.05,
  :nature-exponents [0.4172736548999303],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.3308261784728235 0.2535323219817634],
  :industry 0,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 4,
  :labor-quantities [0],
  :production-inputs [[2 4] [1 2] [1 2]],
  :input-exponents [0.293561688339663 0.15587337892538575],
  :xe 0.05,
  :nature-exponents [0.3040288511470399 0.2147472204341151],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.31098895640628105 0.3052931548745528],
  :industry 0,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 4,
  :labor-quantities [0],
  :production-inputs [[2 4] [1 2] [1 2]],
  :input-exponents [0.1751608756390748 0.2909174262647011],
  :xe 0.05,
  :nature-exponents [0.2767049424266909 0.24532087439349007],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.2590736417662488 0.2712351489495306],
  :industry 0,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 4,
  :labor-quantities [0],
  :production-inputs [[1 3 4] [1] [1 2]],
  :input-exponents
  [0.17611241807280892 0.12684490503225637 0.14033134195837837],
  :xe 0.05,
  :nature-exponents [0.37925133852167175],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.38806133567742906],
  :industry 0,
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 4,
  :labor-quantities [0],
  :production-inputs [[1 3 4] [2] [2]],
  :input-exponents
  [0.17844855495678802 0.17814487117251943 0.14342668771934708],
  :xe 0.05,
  :nature-exponents [0.5542518943635568],
  :a 0.25})
)

(def wcs-old-001
  '({:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.2026329803685868 0.2387609806557065],
    :industry 2,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[2] [1 2] [1 2]],
    :input-exponents [0.31864401315672003],
    :xe 0.05,
    :nature-exponents [0.16513085736109567 0.23589010177981623],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.1700883678367125 0.16710704970204387],
    :industry 2,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[1 2 3 4] [1] [1 2]],
    :input-exponents
    [0.05425433514498952
     0.06242416021235173
     0.07773215689384524
     0.09715265116372684],
    :xe 0.05,
    :nature-exponents [0.28587437969792284],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.4006353376486451],
    :industry 2,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[1 3] [1] [1]],
    :input-exponents [0.15537864966930878 0.11883084967741146],
    :xe 0.05,
    :nature-exponents [0.3940114737676156],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.37843169282894595],
    :industry 2,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[3 4] [2] [2]],
    :input-exponents [0.13855376072936562 0.13089127144169416],
    :xe 0.05,
    :nature-exponents [0.3693536197014548],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.17730580917230965 0.19740308179254137],
    :industry 2,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[1 2 3 4] [1 2] [1 2]],
    :input-exponents
    [0.06075493128206877
     0.051489360909053566
     0.08244503007464227
     0.05893541537329922],
    :xe 0.05,
    :nature-exponents [0.16581677256847926 0.15486411277506074],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.19665608158769185 0.20074966642816328],
    :industry 2,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[2 4] [1 2] [1 2]],
    :input-exponents [0.1819039180143492 0.1972779872764334],
    :xe 0.05,
    :nature-exponents [0.18331824947083109 0.18651080409034343],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.39343405485525024],
    :industry 2,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[2] [1 2] [1]],
    :input-exponents [0.27467547928966274],
    :xe 0.05,
    :nature-exponents [0.21514838678691034 0.20212025011919543],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.21534602746028275 0.22334037578133148],
    :industry 2,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[1 2 4] [2] [1 2]],
    :input-exponents
    [0.1101208765580594 0.11268039999485374 0.10576415170840336],
    :xe 0.05,
    :nature-exponents [0.28162109957134157],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.23667299659857355 0.2453025073487145],
    :industry 2,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[2 3 4] [1 2] [1 2]],
    :input-exponents
    [0.07117642752339313 0.11563204116861829 0.10797148062348241],
    :xe 0.05,
    :nature-exponents [0.24781166269677307 0.1510200414490507],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.2978227799887152],
    :industry 2,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[1 2 3 4] [1 2] [2]],
    :input-exponents
    [0.08838826244621534
     0.08967532372497491
     0.05072258552296274
     0.07427524200978759],
    :xe 0.05,
    :nature-exponents [0.21922548365277464 0.21832217242522284],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.16498304581156661 0.17674942866672186],
    :industry 2,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[1 2] [1] [1 2]],
    :input-exponents [0.11987833711888547 0.12594290765556854],
    :xe 0.05,
    :nature-exponents [0.4458696292706259],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.33126456055023534],
    :industry 2,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[1 2 4] [1 2] [1]],
    :input-exponents
    [0.11344678136654301 0.07218046865287257 0.13224895436628836],
    :xe 0.05,
    :nature-exponents [0.19808157121968345 0.16079098095659583],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.3446630580165943],
    :industry 2,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[1 2 3 4] [1 2] [1]],
    :input-exponents
    [0.06965093743491264
     0.06655903615466054
     0.09941678486590119
     0.09156479136800222],
    :xe 0.05,
    :nature-exponents [0.17701993566130483 0.171436738053007],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.3828296355492615],
    :industry 2,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[2 3] [1 2] [2]],
    :input-exponents [0.14500644033860863 0.17021229180668956],
    :xe 0.05,
    :nature-exponents [0.2396491660836606 0.22338303435940854],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.2989446083900036],
    :industry 2,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[1] [1 2] [2]],
    :input-exponents [0.3594224592748141],
    :xe 0.05,
    :nature-exponents [0.19867168833624052 0.22715782915864233],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.2959318960796158],
    :industry 2,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[1 2 3] [1 2] [1]],
    :input-exponents
    [0.10393023047690149 0.0862796926836229 0.10692833971577453],
    :xe 0.05,
    :nature-exponents [0.21954012578638432 0.18790173523601256],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.38787264453672066],
    :industry 2,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[1 4] [1 2] [2]],
    :input-exponents [0.17472091574471083 0.1309535078778646],
    :xe 0.05,
    :nature-exponents [0.17572434844395202 0.19561676070174155],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.2180338043601559 0.20734309038982365],
    :industry 2,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[2 3 4] [2] [1 2]],
    :input-exponents
    [0.10155755618281054 0.09039012568021468 0.1072946112733664],
    :xe 0.05,
    :nature-exponents [0.3235113574946185],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.35704828982539827],
    :industry 2,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[1 2 3 4] [1 2] [2]],
    :input-exponents
    [0.09089202416643585
     0.05682663798630072
     0.05759608400789158
     0.06562681633886921],
    :xe 0.05,
    :nature-exponents [0.19953762427443056 0.20878739847235414],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.15780617939469255 0.2072649469351303],
    :industry 2,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[1 3 4] [2] [1 2]],
    :input-exponents
    [0.08437754899602345 0.10764759193129741 0.08510864981982048],
    :xe 0.05,
    :nature-exponents [0.43123224373526886],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.24938929145026612 0.16625102302098654],
    :industry 1,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[1 2 3 4] [1 2] [1 2]],
    :input-exponents
    [0.07205045181081375
     0.0581057166143345
     0.08356901755000523
     0.059384589600324965],
    :xe 0.05,
    :nature-exponents [0.2201016041349071 0.19408163025676708],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.36348003895950554],
    :industry 1,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[1 2 3 4] [1 2] [1]],
    :input-exponents
    [0.076814389514254
     0.07109849179681743
     0.054454069817609495
     0.07458281194419321],
    :xe 0.05,
    :nature-exponents [0.24360506979284235 0.23856473144815976],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.42250528169147916],
    :industry 1,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[3] [1 2] [1]],
    :input-exponents [0.31189105387055416],
    :xe 0.05,
    :nature-exponents [0.1990308300306256 0.24871553828706372],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.15096236341218872 0.21617584262198256],
    :industry 1,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[1 4] [2] [1 2]],
    :input-exponents [0.19128713511205417 0.16566747921121106],
    :xe 0.05,
    :nature-exponents [0.3058357450423026],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.4187507255525945],
    :industry 1,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[2] [1] [2]],
    :input-exponents [0.2744830986420318],
    :xe 0.05,
    :nature-exponents [0.3566152498412623],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.20052033874327035 0.22378821835512186],
    :industry 1,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 2,
    :labor-quantities [0],
    :production-inputs [[2] [1] [1 2]],
    :input-exponents [0.23268505438092235],
    :xe 0.05,
    :nature-exponents [0.3062728293431786],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.154915582281192 0.24057094174948773],
    :industry 1,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 2,
    :labor-quantities [0],
    :production-inputs [[3] [1 2] [1 2]],
    :input-exponents [0.2794329678594007],
    :xe 0.05,
    :nature-exponents [0.1574019539880401 0.16227771442618938],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.24587079821896338 0.1775704513560012],
    :industry 1,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 2,
    :labor-quantities [0],
    :production-inputs [[1 2] [1] [1 2]],
    :input-exponents [0.15147398981682028 0.18803031951254978],
    :xe 0.05,
    :nature-exponents [0.3420816406966035],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.19827129265245377 0.17192033414851238],
    :industry 1,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 2,
    :labor-quantities [0],
    :production-inputs [[1 3 4] [2] [1 2]],
    :input-exponents
    [0.10962084985665399 0.09373845494927949 0.10167339623712988],
    :xe 0.05,
    :nature-exponents [0.33357284857826114],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.21843693037447875 0.16211107500008828],
    :industry 1,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 2,
    :labor-quantities [0],
    :production-inputs [[1 2 3 4] [1 2] [1 2]],
    :input-exponents
    [0.07525496463534805
     0.07401099645568922
     0.08114226497462136
     0.06559441032807767],
    :xe 0.05,
    :nature-exponents [0.18754909078694199 0.2220201931284806],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.18871287966035905 0.15120745448643147],
    :industry 1,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 3,
    :labor-quantities [0],
    :production-inputs [[1 4] [2] [1 2]],
    :input-exponents [0.13581471698507072 0.15900452041810206],
    :xe 0.05,
    :nature-exponents [0.44834776371663776],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.18743093639210667 0.1624280480495934],
    :industry 1,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 3,
    :labor-quantities [0],
    :production-inputs [[3 4] [2] [1 2]],
    :input-exponents [0.1781877731658505 0.17121668015581143],
    :xe 0.05,
    :nature-exponents [0.3275151187655047],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.264187279365263],
    :industry 1,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 3,
    :labor-quantities [0],
    :production-inputs [[4] [1 2] [2]],
    :input-exponents [0.32046105620931054],
    :xe 0.05,
    :nature-exponents [0.22368443798533927 0.1526621183811268],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.4425028710826763],
    :industry 1,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 3,
    :labor-quantities [0],
    :production-inputs [[1 2 3 4] [1 2] [1]],
    :input-exponents
    [0.05978727445326083
     0.06099620753221339
     0.054218264126651636
     0.07712926047826776],
    :xe 0.05,
    :nature-exponents [0.21896045387511948 0.2488932969890338],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.4457517996482378],
    :industry 1,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 3,
    :labor-quantities [0],
    :production-inputs [[1 3] [1] [1]],
    :input-exponents [0.11856513542713532 0.10037024414828051],
    :xe 0.05,
    :nature-exponents [0.30923508654961857],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.44346664481703707],
    :industry 1,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 4,
    :labor-quantities [0],
    :production-inputs [[2] [1 2] [2]],
    :input-exponents [0.37487000319133496],
    :xe 0.05,
    :nature-exponents [0.1970975888711733 0.23437487445589128],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.1974996915603123 0.20986377597225098],
    :industry 1,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 4,
    :labor-quantities [0],
    :production-inputs [[1 2 4] [1 2] [1 2]],
    :input-exponents
    [0.11687680800728162 0.0736937674460569 0.07398122744395658],
    :xe 0.05,
    :nature-exponents [0.1808254852048043 0.24501544139308945],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.20338659199537046 0.16334528557151848],
    :industry 1,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 4,
    :labor-quantities [0],
    :production-inputs [[2 3] [1 2] [1 2]],
    :input-exponents [0.10988131346268995 0.11698240264412774],
    :xe 0.05,
    :nature-exponents [0.24838479037053324 0.1645807689342739],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.24527124873047518 0.24586711537212774],
    :industry 1,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 4,
    :labor-quantities [0],
    :production-inputs [[1 3 4] [1 2] [1 2]],
    :input-exponents
    [0.11443067350187515 0.07958812321747902 0.07381563479062432],
    :xe 0.05,
    :nature-exponents [0.24800735888776032 0.15646921120067372],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.42057417311051015],
    :industry 1,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 4,
    :labor-quantities [0],
    :production-inputs [[1 4] [1] [2]],
    :input-exponents [0.1889341101622227 0.16445035036250516],
    :xe 0.05,
    :nature-exponents [0.40677808529003556],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.2173964656584327 0.23676158605638437],
    :industry 0,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[1 2 3 4] [1 2] [1 2]],
    :input-exponents
    [0.07385887431566378
     0.07401590551805715
     0.0743191716375115
     0.08270538429160454],
    :xe 0.05,
    :nature-exponents [0.22459031633164778 0.1735140668649271],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.1933590036246493 0.18772691342272366],
    :industry 0,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[2 3 4] [1 2] [1 2]],
    :input-exponents
    [0.10876092164683851 0.09043976228398455 0.09414441361171709],
    :xe 0.05,
    :nature-exponents [0.19678831261195295 0.20507472091826334],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.16688369061733177 0.17699014523301942],
    :industry 0,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[1 3 4] [1 2] [1 2]],
    :input-exponents
    [0.10151744598723425 0.13135503175980895 0.1189571537044243],
    :xe 0.05,
    :nature-exponents [0.17537108568206883 0.18907583730268496],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.24659256296213833 0.23434533642081692],
    :industry 0,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[2 3] [2] [1 2]],
    :input-exponents [0.14845025415843466 0.11530209543904124],
    :xe 0.05,
    :nature-exponents [0.4301832803358364],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.1867361113877733 0.22712468513694223],
    :industry 0,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 1,
    :labor-quantities [0],
    :production-inputs [[1 2] [1 2] [1 2]],
    :input-exponents [0.14733478320324572 0.1702445864375292],
    :xe 0.05,
    :nature-exponents [0.2328184341374405 0.1710741336127954],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.23422844617160782 0.23613159046724191],
    :industry 0,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 2,
    :labor-quantities [0],
    :production-inputs [[1] [2] [1 2]],
    :input-exponents [0.2668309323320861],
    :xe 0.05,
    :nature-exponents [0.43013685683605196],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.2549694391632788],
    :industry 0,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 2,
    :labor-quantities [0],
    :production-inputs [[2 3] [1 2] [2]],
    :input-exponents [0.15727173012533452 0.10935979286314612],
    :xe 0.05,
    :nature-exponents [0.2335494645917463 0.22119705081207178],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.318744674830463],
    :industry 0,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 2,
    :labor-quantities [0],
    :production-inputs [[2 4] [1 2] [2]],
    :input-exponents [0.19701677163668604 0.1815434880350878],
    :xe 0.05,
    :nature-exponents [0.16845373918636797 0.23729757537507462],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.23594196567705555 0.15268892715200647],
    :industry 0,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 2,
    :labor-quantities [0],
    :production-inputs [[2 3 4] [1 2] [1 2]],
    :input-exponents
    [0.1275132144902364 0.1255670671996042 0.11076555006673333],
    :xe 0.05,
    :nature-exponents [0.22827299615479546 0.22330341426406358],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.19596462343340632 0.24974536688374727],
    :industry 0,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 2,
    :labor-quantities [0],
    :production-inputs [[3 4] [1 2] [1 2]],
    :input-exponents [0.10160813362703537 0.1391212348935273],
    :xe 0.05,
    :nature-exponents [0.2345861681049775 0.2210580956560989],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.15319431613418455 0.23576682407727892],
    :industry 0,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 3,
    :labor-quantities [0],
    :production-inputs [[1 4] [1 2] [1 2]],
    :input-exponents [0.133770693976008 0.142644617766804],
    :xe 0.05,
    :nature-exponents [0.23494859123266593 0.18127513936980658],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.2466237983025905 0.21033135062372704],
    :industry 0,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 3,
    :labor-quantities [0],
    :production-inputs [[1 3 4] [2] [1 2]],
    :input-exponents
    [0.08911682716513998 0.09945441796967758 0.0990012294245814],
    :xe 0.05,
    :nature-exponents [0.38780474286321587],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.3104240315299607],
    :industry 0,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 3,
    :labor-quantities [0],
    :production-inputs [[1 2 3] [1] [1]],
    :input-exponents
    [0.07541149346939924 0.07480393764969105 0.08970357421497996],
    :xe 0.05,
    :nature-exponents [0.3876571300425589],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.28811410730321385],
    :industry 0,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 3,
    :labor-quantities [0],
    :production-inputs [[1 2 4] [1] [2]],
    :input-exponents
    [0.11139756779550608 0.07359494635631895 0.06685281824817643],
    :xe 0.05,
    :nature-exponents [0.2521045075232701],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.21910223897306585 0.20608774192496382],
    :industry 0,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 3,
    :labor-quantities [0],
    :production-inputs [[2 3 4] [1 2] [1 2]],
    :input-exponents
    [0.11834060534453744 0.11094360220876856 0.07031462489959348],
    :xe 0.05,
    :nature-exponents [0.21128425221492067 0.24686327692417764],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.1591360052123456 0.20516378339409633],
    :industry 0,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 4,
    :labor-quantities [0],
    :production-inputs [[2 3] [1] [1 2]],
    :input-exponents [0.18581727347696703 0.12860324420606237],
    :xe 0.05,
    :nature-exponents [0.39192431065067584],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.23294856436058287 0.23270655675469254],
    :industry 0,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 4,
    :labor-quantities [0],
    :production-inputs [[2 4] [2] [1 2]],
    :input-exponents [0.19524615412306368 0.12006175907393785],
    :xe 0.05,
    :nature-exponents [0.31672213379087233],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.2731554761600218],
    :industry 0,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 4,
    :labor-quantities [0],
    :production-inputs [[1 4] [1 2] [2]],
    :input-exponents [0.10261106720604155 0.12807487187930194],
    :xe 0.05,
    :nature-exponents [0.15164534700107657 0.1770789901797858],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.2475451070804174 0.20268891124217991],
    :industry 0,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 4,
    :labor-quantities [0],
    :production-inputs [[1 2 3] [1] [1 2]],
    :input-exponents
    [0.1318928476335303 0.07913580112099786 0.09630448912200434],
    :xe 0.05,
    :nature-exponents [0.25554998214622654],
    :a 0.25}
   {:effort 0.5,
    :cq 0.25,
    :ce 1,
    :labor-exponents [0.22387387666835512 0.18202693588841945],
    :industry 0,
    :output 0,
    :s 1,
    :du 7,
    :c 0.05,
    :product 4,
    :labor-quantities [0],
    :production-inputs [[4] [1] [1 2]],
    :input-exponents [0.3324485530429372],
    :xe 0.05,
    :nature-exponents [0.26512775941570543],
    :a 0.25}))

