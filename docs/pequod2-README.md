## WHAT IS IT?

This Netlogo model--the Participatory Planning Procedure Prototype (PPPP, AKA _Pequod_)--is a feasibility study for an aspect of participatory economics, which requires participatory economic planning in iterations between councils composed of workers and councils composed of consumers. These councils are the agents of the system. Factory icons represent worker councils and house icons represent consumer councils.

Participatory economics is a model for a new economy based on democracy, justice, and ecological sustainability proposed as an alternative to current Western economic systems. For an introduction to the primary concepts of participatory economics, see [ParticipatoryEconomics.info](http://participatoryeconomics.info), which provides free e-books and other informational materials.

Macroscopic aspects of this Netlogo model are documented here. The variables, procedures, and reporters of the model are individually documented in the Code tab. A number of BehaviorSpace experiments have also been configured, which are documented in the section _How to Use It_.

EDIT: A non-competitive market model can reach the same Equilibrium prices in a reasonable number of iterations. [convergence, BS exp's...] [rosenbrach search method...a heuristic search method to solution...heuristic optimization]

## HOW IT WORKS

### Background Microeconomic Theory

Some knowledge of microeconomics is necessary in order to explore participatory economic planning with the model, but some basics are explained here.

Economic production is quantified by supply, and economic consumption is quantified by demand. When there is an imbalance between these, there is said to be a surplus. An important activity of economic planning is _quickly_ reducing surplus. Commonly in economic literature these quantities are calculated using the Cobb-Douglas production/utility functions. For supply the production function is used and for demand the utility function is used. Each has the same general form, which is

<center>Quantity = A(Input<sub>1</sub><sup>Elasticity<sub>1</sub></sup> * Input<sub>2</sub><sup>Elasticity<sub>2</sub></sup> * ... * Input<sub>N</sub><sup>Elasticity<sub>N</sub></sup>),</center>

where A represents the total factor of productivity or utility, the inputs are things like resources, and the elasticities measure the responsiveness of the total quantity to a change in levels of each input quantity, which facilitates fine-tuning of unique outcomes for each economic agent.

Supply and demand are normally represented graphically as curves of prices with respect to quantities.

### The Model

This Netlogo model centers around planning iterations, with the goal of quickly reducing surplus by matching supply and demand of consumer goods. Worker and consumer councils make proposals concerning what they want to produce and consume, based upon current estimates of opportunity and social costs as shown by the indicative prices for each good. The indicative prices are adjusted between each iteration, and the iterations stop when demand is within a configurable threshold of the supply for each economic category (industries, resources, etc.). Though not a part of this Netlogo model, the authors assume a yearly planning cycle will occur if this planning model is implemented in a real economy. For example, the planning process modeled could occur within the month of December for use starting in January for that year.

#### Technical notes

Requiring exact equality between supply and demand is unnecessary because the next year's plan is assumed to begin with parameters resulting in an initial divergence, though the initial price vector should be very similar to that reached during the prior planning cycle.

The solutions to the production and utility functions are obtained analytically with a computer algebra system, and then added to the included file production-proposals.nls.

## HOW TO USE IT

### Graphs

The most important aspects of the interface are the graphs of price and quantity histories. If/when the surplus threshold is reached and the iterations stop, the number of iterations is the primary quantity of interest, followed by the shape of the graph that led to that equilibrium state. Following convergence, the particular values of each category at which equilibrium was reached are given in the middle of the interface, below the parameter controls.

### Control Parameters

The model contains more parameters than are accessible in the interface, each of which is documented at the top of the Code tab. The controllable parameters of primary interest to researchers in the model's current state are those in the middle of the interface. At the bottom of the interface are parameters of interest in exploring performance enhancements, or for debugging purposes.

#### Surplus threshold

This is the threshold, as the ratio of supply to demand, at or under which the model will cease iteration. Default: 1%.

#### Price delta

This moderates the rate at which price adjustments are made. Default: 10%.

#### Initial price vector

This is the list of starting prices for each input quantity.

#### Experiment number

This facilitates pseudo-randomization of the initial conditions affecting model behavior and heterogeneity of agents, which facilitates replication of results. That is, _all else being equal_, different experiment numbers produce different behavior, and running the model with the same experiment number will always produce the same behavior, allowing interesting behavior to be further explored and shared with colleagues. If you wish to explore the same initial conditions for varying populations, leave all but the experiment constant, and if you wish to explore changing parameters within the same population, change anything but the experiment.

#### Workers per council

This is the number of units of labor supplied to the general labor supply by each consumer council. The labor-supply reporter reflects this as the arithmetic product of this and the number of consumer councils.

#### Natural resources supply

This is the quantity of one input to the production functions, a hypothetical natural resource. Default: 1000.

### BehaviorSpace Experiments

By going to Tools > BehaviorSpace, users will find a number of per-configured experiments that can be executed to explore the dynamics of the model under specific initial conditions. The results are tabulated for analysis.

## THINGS TO NOTICE

### Number of Ticks/Iterations

Reducing the number of ticks, which correspond to iterations in council negotiations, is a primary objective of model experiments. The authors suggest that approximately seven ticks is a maximum, which makes the processes feasible for human activity. When exploring the model, take note of what parameter combinations result in the smallest number of ticks.

### Price/Quantity Dynamics

As time progresses, the price and quantity values change in classifiable patterns. Take note of what gives rise to classes of dynamics, and look for new classes of dynamics.

## THINGS TO TRY

### Control Parameters

Though any parameter can be modified, and the outcome dynamics explored, three parameters are of primary interest in exploring paths to rapid convergence of supply and demand.

#### Surplus threshold

This value has been found to be inversely proportional to the number of ticks to "convergence", but it must be decided what distances between supply and demand are acceptable.

#### Price delta

This value has been found to be inversely proportional to the number of ticks to convergence, but beyond a certain increasing value of the parameter, it is directly proportional to the number of ticks to convergence. Therefore, for any given experiment there exists an optimal delta, which leads to convergence in the fewest number of iterations. Current experimental results suggests that the optimal range is between 0.05 and 0.15.

#### Initial price vector

As expected, for any given set of initial conditions, as these values approach the converging price vector, fewer ticks are needed to reach that converging set of values.

## EXTENDING THE MODEL

The immediate objectives of the research team are to

* increase the number of councils,

* increase the number of produced goods and add public goods,

* increase the categories of labor,

* increase the number of primary resources,

* relax convexity conditions on the production and utility functions, which is to allow the exponents to sum to a number greater than one,

* add pollutants/externalities,

* and to create a set of protocols for more meaningful BehaviorSpace experiments.

## CREDITS AND REFERENCES

This model is based upon the work of Robin Hahnel and Michael Albert on participatory economics. Along with Robin Hahnel, the researchers developing the model's conceptual and computational aspects are Michael Weisdorf, Christan Echt, and Nick Gilla.

The model can be shared and modified under the terms of the GPL version 3. The code can be downloaded from and changes submitted to its [Github repository](https://github.com/ParticipatoryEconomics/pequod).

[ParticipatoryEconomics.info](http://participatoryeconomics.info) provides free e-books and other informational materials on participatory economics.

(Current model uses Netlogo 5.3.1)
