(ns pequod-cljs.ex2)

(def ccs1
[{:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.11323324130939968
   0.1364616684896434
   0.1256238095731262
   0.09973321834415629],
  :final-demands [0 0 0 0 0],
  :cy 7.816114419058129,
  :public-good-exponents [0.1589606550542937],
  :public-good-demands [0],
  :pollution-supply-coefficients [-0.14516232995790718],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.12476883342995175
   0.1328510597273455
   0.09213562083757237
   0.09578508392158029],
  :final-demands [0 0 0 0 0],
  :cy 7.835660744327954,
  :public-good-exponents [0.1611902262754466],
  :public-good-demands [0],
  :pollution-supply-coefficients [-0.13810497722366072],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.15824054930479375
   0.13512240229196457
   0.1455935383559089
   0.10382657544366089],
  :final-demands [0 0 0 0 0],
  :cy 11.910217375497348,
  :public-good-exponents [0.09765466883095542],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.12449561307390583],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.08609752474845475
   0.11038870673510419
   0.09352008140514413
   0.13335154701652407],
  :final-demands [0 0 0 0 0],
  :cy 6.054157307613287,
  :public-good-exponents [0.09544035179257893],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.14976405278884547],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.1373633390121042
   0.11663684709104197
   0.16183835009762687
   0.13653246931846533],
  :final-demands [0 0 0 0 0],
  :cy 7.668716496470303,
  :public-good-exponents [0.1613223804391446],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.13215241916989945],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.13590122201703017
   0.15999240313653346
   0.12997041380381713
   0.0884929707827839],
  :final-demands [0 0 0 0 0],
  :cy 14.602462964935425,
  :public-good-exponents [0.14729085288090116],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.13055065834128882],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.12031539359498529
   0.1244093466792425
   0.16435954803411804
   0.16247756416654424],
  :final-demands [0 0 0 0 0],
  :cy 6.358400450138277,
  :public-good-exponents [0.15589948518889127],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.13345411041620145],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.11012274067265332
   0.1249155439675742
   0.1253322765261924
   0.16584782946565851],
  :final-demands [0 0 0 0 0],
  :cy 6.311162180736464,
  :public-good-exponents [0.12827354701555438],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.16576343553076373],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.1532794701426045
   0.12353321354082143
   0.16641307642702113
   0.09989891657667838],
  :final-demands [0 0 0 0 0],
  :cy 14.272763953760121,
  :public-good-exponents [0.16237868401963945],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.10182096339903131],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.16577787609474992
   0.14429405552059746
   0.09075856269220291
   0.10586347413807493],
  :final-demands [0 0 0 0 0],
  :cy 10.190500597839339,
  :public-good-exponents [0.14803839132230284],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.14251041555659202],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.1351232311597172
   0.10787434624124984
   0.11346555661757515
   0.1581161078169157],
  :final-demands [0 0 0 0 0],
  :cy 8.138260313234094,
  :public-good-exponents [0.10483509714246819],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.16218800833449476],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.1510441008067539
   0.09152586805432822
   0.1467815035435775
   0.1446471669179302],
  :final-demands [0 0 0 0 0],
  :cy 9.9288376887495,
  :public-good-exponents [0.15901736768260538],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.1537173652457811],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.10232613122264408
   0.13212201137385407
   0.16476349366725368
   0.14501669477994975],
  :final-demands [0 0 0 0 0],
  :cy 7.560923443651442,
  :public-good-exponents [0.16479719704505685],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.08683070077092961],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.14392601584951212
   0.12375696317638127
   0.13892724865103984
   0.15150232939553554],
  :final-demands [0 0 0 0 0],
  :cy 8.31359876388659,
  :public-good-exponents [0.1168837800068254],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.1385479013321443],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.16473142969868024
   0.13836920594997887
   0.09169162732206818
   0.15758666748724104],
  :final-demands [0 0 0 0 0],
  :cy 7.302919829881139,
  :public-good-exponents [0.15648979292746784],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.10445296680641812],
  :income 5000}])

(def ccs2
[{:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.13798912357781964
   0.10070244433719007
   0.12991045620860564
   0.10895716540977587],
  :final-demands [0 0 0 0 0],
  :cy 8.56397420736308,
  :public-good-exponents [0.16272543018824231],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.18771668132772779],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.10304599214860184
   0.13817309016614213
   0.09988368964991426
   0.13125857565740637],
  :final-demands [0 0 0 0 0],
  :cy 6.994747043586388,
  :public-good-exponents [0.16299741223636752],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.20763376114344975],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.11191014530724053
   0.1463959028925998
   0.08337307273300777
   0.11281949194085103],
  :final-demands [0 0 0 0 0],
  :cy 11.544025695815098,
  :public-good-exponents [0.13048171497175604],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.2774617635816541],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.1488761951300433
   0.13922434807830691
   0.11512119603329098
   0.1225200800616145],
  :final-demands [0 0 0 0 0],
  :cy 9.922804826088438,
  :public-good-exponents [0.1346783780674263],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.27124846048991613],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.1583232508419135
   0.1281823752501282
   0.154282745097633
   0.1607337825933952],
  :final-demands [0 0 0 0 0],
  :cy 6.115347580135209,
  :public-good-exponents [0.11191406713521515],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.17711725319865118],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.12245320929712177
   0.10438504378986875
   0.15118556091176982
   0.156144269863704],
  :final-demands [0 0 0 0 0],
  :cy 13.503451553489107,
  :public-good-exponents [0.11962209693601866],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.30464481705811186],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.1515700654560022
   0.1559718664428845
   0.11974026473874788
   0.12262732886961664],
  :final-demands [0 0 0 0 0],
  :cy 8.537042098235961,
  :public-good-exponents [0.15053405430297245],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.180238824762822],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.12912033259913736
   0.15635965634702403
   0.08804426956762612
   0.09116680482565134],
  :final-demands [0 0 0 0 0],
  :cy 12.02192916871094,
  :public-good-exponents [0.11830066971020546],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.31935723965211876],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.15307847063916835
   0.11343207105250909
   0.14469598894854532
   0.14957773255168855],
  :final-demands [0 0 0 0 0],
  :cy 7.449786488927885,
  :public-good-exponents [0.13205431092562028],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.3283622669868985],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.11221182332980656
   0.16132760940329804
   0.13133388283310332
   0.13516517370952644],
  :final-demands [0 0 0 0 0],
  :cy 12.449220235710248,
  :public-good-exponents [0.15040860933695194],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.2591685516092739],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.10245357467795399
   0.14969877845212876
   0.09623745618744114
   0.09881296594111348],
  :final-demands [0 0 0 0 0],
  :cy 8.037065999549712,
  :public-good-exponents [0.14167682052204025],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.2017887230560678],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.09759722737200278
   0.11329410928432637
   0.09607622573324562
   0.1146508878275154],
  :final-demands [0 0 0 0 0],
  :cy 12.663829132945665,
  :public-good-exponents [0.12662370884925125],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.23323917583372505],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.10864489025985931
   0.12581959300291715
   0.10000964528896414
   0.1349715174436861],
  :final-demands [0 0 0 0 0],
  :cy 9.460450697735887,
  :public-good-exponents [0.15764275249596388],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.20044134112340145],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.09512501631281141
   0.1279295925165601
   0.09712488396183178
   0.09974834674177273],
  :final-demands [0 0 0 0 0],
  :cy 13.370299844173791,
  :public-good-exponents [0.1587198176995328],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.2903859323661791],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.12849614341589538
   0.13361792825353425
   0.15647478566940665
   0.11117496664896691],
  :final-demands [0 0 0 0 0],
  :cy 12.312806173595622,
  :public-good-exponents [0.11609132290935728],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.2940269902845527],
  :income 5000}]
)

(def ccs3
[{:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.16355080204949207
   0.14178986219930687
   0.15889013741875763
   0.09815236039380337],
  :final-demands [0 0 0 0 0],
  :cy 10.174063633611647,
  :public-good-exponents [0.10113793005501812],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.49077432856208947],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.14486694227616329
   0.12987333492758643
   0.14784004268025175
   0.09755180841294034],
  :final-demands [0 0 0 0 0],
  :cy 6.71527986775924,
  :public-good-exponents [0.11425749243217068],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.3690416331955424],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.10154406802210722
   0.16551056408094864
   0.12878113976714628
   0.136663861336782],
  :final-demands [0 0 0 0 0],
  :cy 6.07194937315672,
  :public-good-exponents [0.09053382607256073],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.25151518707186865],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.1666296983716566
   0.10914425282663542
   0.14302947972048496
   0.09938292518852511],
  :final-demands [0 0 0 0 0],
  :cy 10.233864956351479,
  :public-good-exponents [0.08548868230452031],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.29881377773227247],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.1423923722106786
   0.12667680937288153
   0.09006263295861883
   0.11881472500615381],
  :final-demands [0 0 0 0 0],
  :cy 9.688517770945053,
  :public-good-exponents [0.15070977876153463],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.2686878512288235],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.09238057673262728
   0.16385898972087076
   0.10490366914891315
   0.11245008102500723],
  :final-demands [0 0 0 0 0],
  :cy 11.600289184385446,
  :public-good-exponents [0.16001363503584526],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.39956965016116974],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.1187688185704585
   0.13926475570185962
   0.1256380632714659
   0.1330772164382497],
  :final-demands [0 0 0 0 0],
  :cy 8.710025710152014,
  :public-good-exponents [0.0912851592644523],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.4834516049244512],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.09243474431151656
   0.15237902389645663
   0.15091875510985936
   0.1576390585339736],
  :final-demands [0 0 0 0 0],
  :cy 7.928643200730789,
  :public-good-exponents [0.14196275303634653],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.3862567769133734],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.16026504152887677
   0.14300905640264377
   0.164820064076686
   0.11596618672112488],
  :final-demands [0 0 0 0 0],
  :cy 12.460700907063496,
  :public-good-exponents [0.1321652232029099],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.3286022723386332],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.11405013350398628
   0.099734676195114
   0.1192142145866668
   0.1647149753521963],
  :final-demands [0 0 0 0 0],
  :cy 14.1368385083645,
  :public-good-exponents [0.14662116836073208],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.38204115233810887],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.12431023835196281
   0.14868820422247897
   0.09751916998817482
   0.09533593839623006],
  :final-demands [0 0 0 0 0],
  :cy 12.875331313673103,
  :public-good-exponents [0.09519593447227839],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.34342466172102487],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.16288927503003836
   0.08991873216875321
   0.10102789676784876
   0.0907270091652635],
  :final-demands [0 0 0 0 0],
  :cy 9.657864458259963,
  :public-good-exponents [0.12114514285589323],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.371368461201096],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.09477177604530733
   0.12686383295843767
   0.15786807354804777
   0.08841107006503793],
  :final-demands [0 0 0 0 0],
  :cy 10.710998267696212,
  :public-good-exponents [0.13030336504119708],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.47760389681667426],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.10977183346811353
   0.09761009674181884
   0.11997111528425058
   0.1194186173716117],
  :final-demands [0 0 0 0 0],
  :cy 6.286153667213801,
  :public-good-exponents [0.10131247067207697],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.4128527277293281],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.11977426310280598
   0.14175042505226962
   0.12143158051634226
   0.14312755695439575],
  :final-demands [0 0 0 0 0],
  :cy 6.184935828341531,
  :public-good-exponents [0.10397626151861245],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.4881010780513487],
  :income 5000}]
)
 

  (def wcs
[#_{:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.09935352394548516],
  :industry 2,
  :pollutant-exponents [0.010718992535000137],
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [1] [1] [1]],
  :input-exponents
  [0.04329853795869807
   0.046309813318193276
   0.010661673691089668
   0.0028467943221126],
  :xe 0.05,
  :nature-exponents [0.1293192056482164],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.1028135837115091846],
  :industry 1,
  :pollutant-exponents [0.0506247002196812135],
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[2 3 4] [1] [1] [1]],
  :input-exponents
  [0.1028395340831637837 0.1051012103511783424 0.1026041935226524322],
  :xe 0.05,
  :nature-exponents [0.15830300871834108],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.14225223945364215],
  :industry 1,
  :pollutant-exponents [0.0509783213289809423],
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 2,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [1] [1] [1]],
  :input-exponents
  [0.1024259343354575216
   0.100025546022025140224
   0.1045582750907660396
   0.1010072492709029934],
  :xe 0.05,
  :nature-exponents [0.17114360349958974],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.1059561698032832004],
  :industry 1,
  :pollutant-exponents [0.0518300585669427558],
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 3,
  :labor-quantities [0],
  :production-inputs [[1 4] [1] [1] [1]],
  :input-exponents [0.10317881202768294 0.107368782212382892],
  :xe 0.05,
  :nature-exponents [0.107365004303918443],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.103898926413162349],
  :industry 1,
  :pollutant-exponents [0.0504134408951774589],
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 4,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [1] [1] [1]],
  :input-exponents
  [0.1002613803363984535
   0.104687635904715819
   0.10235250980842982
   0.1036686195145560874],
  :xe 0.05,
  :nature-exponents [0.15772917366242398],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.1018877216754667983],
  :industry 0,
  :pollutant-exponents [0.0512275388492131843],
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [1] [1] [1]],
  :input-exponents
  [0.103795106825185634
   0.10013785408615739959
   0.103344902652351769
   0.101804463230505914],
  :xe 0.05,
  :nature-exponents [0.12483588242156488],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.1884777170868126],
  :industry 0,
  :pollutant-exponents [0.0514109227307426592],
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 2,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [1] [1] [1]],
  :input-exponents
  [0.1019815239562791766
   0.1043130101445939574
   0.1009240755973502357
   0.10007318195010368567],
  :xe 0.05,
  :nature-exponents [0.12477735759171629],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.106505369404588182],
  :industry 0,
  :pollutant-exponents [0.05007042814060128766],
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 3,
  :labor-quantities [0],
  :production-inputs [[3] [1] [1] [1]],
  :input-exponents [0.1376063572667742],
  :xe 0.05,
  :nature-exponents [0.106435987209037308],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.108714647882116121],
  :industry 0,
  :pollutant-exponents [0.0503565055052977049],
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 4,
  :labor-quantities [0],
  :production-inputs [[1 3 4] [1] [1] [1]],
  :input-exponents
  [0.10227497566461492 0.1023973286200220035 0.1020152030581462317],
  :xe 0.05, 
  :nature-exponents [0.1041930420272733485], 
  :a 0.25}
{:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.12053781079086186], :industry 1, :pollutant-exponents [0.042936009654518104], :output 0, :s 1, :du 7, :c 0.05, :product 1, :labor-quantities [0], :production-inputs [[1] [1] [1] [1]], :input-exponents [0.19610490933128724], :xe 0.05, :nature-exponents [0.044239481961317106], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.15057549540898396], :industry 1, :pollutant-exponents [0.05177142453811804], :output 0, :s 1, :du 7, :c 0.05, :product 1, :labor-quantities [0], :production-inputs [[1 2 4] [1] [1] [1]], :input-exponents [0.004909623097844847 0.02130905272179328 0.03765927333775697], :xe 0.05, :nature-exponents [0.09806647884332886], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.2557054541843074], :industry 1, :pollutant-exponents [0.07797391103080051], :output 0, :s 1, :du 7, :c 0.05, :product 1, :labor-quantities [0], :production-inputs [[1 2 3 4] [1] [1] [1]], :input-exponents [0.01012626335746985 0.03344672918513931 0.0038767390725469256 0.03589146796516562], :xe 0.05, :nature-exponents [0.01327143605006882], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.06662681317758377], :industry 1, :pollutant-exponents [0.0765870175143406], :output 0, :s 1, :du 7, :c 0.05, :product 1, :labor-quantities [0], :production-inputs [[1 2 3 4] [1] [1] [1]], :input-exponents [0.018421774055268608 0.0062944814872779534 0.017816374768479217 0.014882757794295765], :xe 0.05, :nature-exponents [0.15128373774778353], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.11612251527354989], :industry 1, :pollutant-exponents [0.006880845713398531], :output 0, :s 1, :du 7, :c 0.05, :product 2, :labor-quantities [0], :production-inputs [[2 3 4] [1] [1] [1]], :input-exponents [0.06187479659373499 0.025572081025105886 0.016747000736306143], :xe 0.05, :nature-exponents [0.15176044813502693], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.14435187231104069], :industry 1, :pollutant-exponents [0.06180448375838027], :output 0, :s 1, :du 7, :c 0.05, :product 2, :labor-quantities [0], :production-inputs [[1 2 3 4] [1] [1] [1]], :input-exponents [0.011809041004061034 0.011344209392443495 0.03406433766313861 0.007864193399602676], :xe 0.05, :nature-exponents [0.19332286131594847], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.09323509231781144], :industry 1, :pollutant-exponents [0.06767734569498562], :output 0, :s 1, :du 7, :c 0.05, :product 2, :labor-quantities [0], :production-inputs [[1 2 3 4] [1] [1] [1]], :input-exponents [0.010343696362549405 2.3195085886152378E-4 0.02599845610366763 0.03342014992959499], :xe 0.05, :nature-exponents [0.039412650059119564], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.1603915639621022], :industry 1, :pollutant-exponents [0.04452114107211307], :output 0, :s 1, :du 7, :c 0.05, :product 2, :labor-quantities [0], :production-inputs [[3] [1] [1] [1]], :input-exponents [0.16289671887351098], :xe 0.05, :nature-exponents [0.0727446986497023], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [9.622337072814347E-5], :industry 1, :pollutant-exponents [0.09298557964473105], :output 0, :s 1, :du 7, :c 0.05, :product 3, :labor-quantities [0], :production-inputs [[1] [1] [1] [1]], :input-exponents [0.08131763177320402], :xe 0.05, :nature-exponents [0.19045687904448028], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.24629029745889733], :industry 1, :pollutant-exponents [0.029262052956717455], :output 0, :s 1, :du 7, :c 0.05, :product 3, :labor-quantities [0], :production-inputs [[1 2 3 4] [1] [1] [1]], :input-exponents [0.009125951639007302 0.030392984123787988 0.04404321540869675 0.01115395628484196], :xe 0.05, :nature-exponents [0.0840621122860527], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.24614778026599804], :industry 1, :pollutant-exponents [0.07537491812212521], :output 0, :s 1, :du 7, :c 0.05, :product 3, :labor-quantities [0], :production-inputs [[1 2 3] [1] [1] [1]], :input-exponents [0.06581287108153024 0.05052005021887024 0.04710052544133553], :xe 0.05, :nature-exponents [0.04302089219015229], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.2504118017442466], :industry 1, :pollutant-exponents [0.04595952567430165], :output 0, :s 1, :du 7, :c 0.05, :product 3, :labor-quantities [0], :production-inputs [[1 2 3 4] [1] [1] [1]], :input-exponents [0.01651696666491284 0.02246235181654845 0.03539884306090861 0.04958286929820746], :xe 0.05, :nature-exponents [0.08607313010729943], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.23529369783879683], :industry 1, :pollutant-exponents [0.027310655952544907], :output 0, :s 1, :du 7, :c 0.05, :product 4, :labor-quantities [0], :production-inputs [[2 3 4] [1] [1] [1]], :input-exponents [0.061648873195093754 0.06334493634974885 3.6898975141995324E-4], :xe 0.05, :nature-exponents [0.023099908481826747], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.007136949752553058], :industry 1, :pollutant-exponents [0.06182325446707644], :output 0, :s 1, :du 7, :c 0.05, :product 4, :labor-quantities [0], :production-inputs [[1 2 3] [1] [1] [1]], :input-exponents [0.055825824465521416 0.06109372050146915 0.010589712967936848], :xe 0.05, :nature-exponents [0.08198629095478585], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.15987005549804373], :industry 1, :pollutant-exponents [0.006113702320472348], :output 0, :s 1, :du 7, :c 0.05, :product 4, :labor-quantities [0], :production-inputs [[2 4] [1] [1] [1]], :input-exponents [0.06136331479179104 0.08816203103075193], :xe 0.05, :nature-exponents [0.13042606483813343], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.2965810895704391], :industry 1, :pollutant-exponents [0.08816809232808523], :output 0, :s 1, :du 7, :c 0.05, :product 4, :labor-quantities [0], :production-inputs [[1 2 3] [1] [1] [1]], :input-exponents [0.006343176916683572 0.0023543737425441467 0.06598949717109193], :xe 0.05, :nature-exponents [0.15837660855912108], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.025824748245467072], :industry 0, :pollutant-exponents [0.04264747770562394], :output 0, :s 1, :du 7, :c 0.05, :product 1, :labor-quantities [0], :production-inputs [[1 3] [1] [1] [1]], :input-exponents [0.0840117245693811 0.05953723543606526], :xe 0.05, :nature-exponents [0.00878474170293957], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.24581199545368027], :industry 0, :pollutant-exponents [0.005594511415970793], :output 0, :s 1, :du 7, :c 0.05, :product 1, :labor-quantities [0], :production-inputs [[3] [1] [1] [1]], :input-exponents [0.1480803057925717], :xe 0.05, :nature-exponents [0.05453753303111402], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.1238806727389561], :industry 0, :pollutant-exponents [0.018975932905149175], :output 0, :s 1, :du 7, :c 0.05, :product 1, :labor-quantities [0], :production-inputs [[1 2 3 4] [1] [1] [1]], :input-exponents [0.04584410523779061 0.04236422864077258 0.042476795383984765 0.025554695215485874], :xe 0.05, :nature-exponents [0.14370832181466264], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.2550661880200832], :industry 0, :pollutant-exponents [0.08617382156885935], :output 0, :s 1, :du 7, :c 0.05, :product 1, :labor-quantities [0], :production-inputs [[1 2 3 4] [1] [1] [1]], :input-exponents [0.006030299837926728 0.007774716108749136 0.026247286944093978 0.04970852429746684], :xe 0.05, :nature-exponents [0.04640768688828101], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.29752817239972984], :industry 0, :pollutant-exponents [0.0770408296614537], :output 0, :s 1, :du 7, :c 0.05, :product 2, :labor-quantities [0], :production-inputs [[1 3] [1] [1] [1]], :input-exponents [0.08021195968926373 0.013597904524780102], :xe 0.05, :nature-exponents [0.14664874611439666], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.09622286622267481], :industry 0, :pollutant-exponents [0.03519760381830119], :output 0, :s 1, :du 7, :c 0.05, :product 2, :labor-quantities [0], :production-inputs [[1 4] [1] [1] [1]], :input-exponents [0.08893662752814407 0.06051515283058883], :xe 0.05, :nature-exponents [0.18944607940638866], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.18342119361081247], :industry 0, :pollutant-exponents [0.06260654312520456], :output 0, :s 1, :du 7, :c 0.05, :product 2, :labor-quantities [0], :production-inputs [[2 3 4] [1] [1] [1]], :input-exponents [0.049604593048326706 0.05552242590943757 0.02484228883759719], :xe 0.05, :nature-exponents [0.025008216420369503], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.0076329042168782795], :industry 0, :pollutant-exponents [0.07172578269426587], :output 0, :s 1, :du 7, :c 0.05, :product 2, :labor-quantities [0], :production-inputs [[2] [1] [1] [1]], :input-exponents [0.08648204628100715], :xe 0.05, :nature-exponents [0.11863259292874027], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.10718493964646436], :industry 0, :pollutant-exponents [0.015340311984586241], :output 0, :s 1, :du 7, :c 0.05, :product 3, :labor-quantities [0], :production-inputs [[1] [1] [1] [1]], :input-exponents [0.003179990946765754], :xe 0.05, :nature-exponents [0.16712848128406899], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.04020796689414853], :industry 0, :pollutant-exponents [0.020166730484512885], :output 0, :s 1, :du 7, :c 0.05, :product 3, :labor-quantities [0], :production-inputs [[1 3 4] [1] [1] [1]], :input-exponents [0.01669069940510663 0.02367583513907421 0.002275469944621496], :xe 0.05, :nature-exponents [0.14709660585077353], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.20640510954203767], :industry 0, :pollutant-exponents [0.07265748773367642], :output 0, :s 1, :du 7, :c 0.05, :product 3, :labor-quantities [0], :production-inputs [[3 4] [1] [1] [1]], :input-exponents [0.04987371751016245 0.014658472769738862], :xe 0.05, :nature-exponents [0.08579345099240326], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.10603581163455272], :industry 0, :pollutant-exponents [0.012715206013063286], :output 0, :s 1, :du 7, :c 0.05, :product 3, :labor-quantities [0], :production-inputs [[3] [1] [1] [1]], :input-exponents [0.12847014297462994], :xe 0.05, :nature-exponents [0.15491059930404996], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.21829387984187468], :industry 0, :pollutant-exponents [9.311979086361833E-4], :output 0, :s 1, :du 7, :c 0.05, :product 4, :labor-quantities [0], :production-inputs [[1 3 4] [1] [1] [1]], :input-exponents [0.046030938518044934 0.044195040446976974 0.05063008152328206], :xe 0.05, :nature-exponents [0.017497987562582498], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.01526021805501047], :industry 0, :pollutant-exponents [0.007836593127604208], :output 0, :s 1, :du 7, :c 0.05, :product 4, :labor-quantities [0], :production-inputs [[3] [1] [1] [1]], :input-exponents [0.025260777378502408], :xe 0.05, :nature-exponents [0.10231140638932464], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.01126135805902746], :industry 0, :pollutant-exponents [0.08123530316509373], :output 0, :s 1, :du 7, :c 0.05, :product 4, :labor-quantities [0], :production-inputs [[1 2 3] [1] [1] [1]], :input-exponents [0.048023914686200396 0.05087897874300286 0.025531989225209488], :xe 0.05, :nature-exponents [0.07573622345962872], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.18490108003493327], :industry 0, :pollutant-exponents [0.030539894217578814], :output 0, :s 1, :du 7, :c 0.05, :product 4, :labor-quantities [0], :production-inputs [[1 3] [1] [1] [1]], :input-exponents [0.09480390348210584 0.06501914229778168], :xe 0.05, :nature-exponents [0.18130929447028665], :a 0.25}
])

  
