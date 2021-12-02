# codekata-16
Exploration and possibly solutions for [kata 16](http://codekata.com/kata/kata16-business-rules/) from codekata.com

### My observations of the description of the payment system
1. The client's business can receive payments for a wide variety of physical and non-physical products (maybe even services?)    
2. Payment for a product from a certain category (or even for a certain product) may trigger arbitrary business processes
3. The business rules for processes are not codified well, the client does not have a central knowledge base of the payment rules, some rules are only known to certain employees
4. Maybe not all processes written in the rules can be automated, some might need manual intervention 
5. The system needs to be flexible: the rules can often change, company employees should be able to add or remove rules
6. We'd like to minimise software engineer support, we don't want to change code and redeploy just because a rule changed
7. Big company => high throughput is needed (this seems to contradict that some processes are manual..)

#### About customer defined rules
+ From points 3, 5 and 6 it follows that we need to make the system 'scriptable', the client has to be able to define and change payment processes
+ Payment process DSL: create a simple language to define rules.
+ nice to have: a tool to check if your rules are valid
+ nice to have: some kind of GUI no-code rule editor
+ some rules might always need manual intervention: the DSL has to include a way to define non-automated tasks for employees
+ the progress of non-automated tasks should be followed, be queryable
+ a rule repository is needed (could be a git repo in the beginning)
+ the system should get notified when the rules change
+ the ruleset should be versioned


#### TODO

add domain tests

add services for:
+ publishing/handling business tasks
+ acquiring the current ruleset
+ acquiring the event stream of payments (gRPC?)

