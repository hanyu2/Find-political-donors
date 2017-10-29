# [Insight Data Engineer Porject](https://github.com/InsightDataScience/find-political-donors)

# Approach
## Data structure
1. Used `Map<String, List<Integer>>` to keep record of contributions from a zip, used binary search to insert a single contribution to make the list of contribution ordered to get the running median efficiently.
2. Use `TreeMap<String, TreeMap<String, List<Integer>>>` to keep record of a recipient of contributions on a single date, used the same way as in zip to keep contributions in order, and used TreeMap to keep order of recipient and time.
## OOD
Used interface, abstract classes, etc. to implement algorithm in an object-oriented way to make project extendable, scalable and clean.    
Data was read line by line in `DateReader`, and each line was applied different analyzers(extended from `Analyzer`) which has its own operations(filter, save, calculate, writeToFile) on this record.  
Analyzer implemented `Filter` interface to make its sub-classes have own ways of filtering the record.  
A single record was treated as a `Record` object to extract fields from records and have a more convenient way of passing and handling records.  
UML diagram for this project:  
![uml](https://image.ibb.co/dAp1d6/find_donor_uml.jpg)

# Run instruction
Put test folders under `/insight_testsuite/tests/`  
Under root directory, run
```
./insight_testsuite/run_test.sh
```
*I changed `run_test.sh` at line 104 from `cd ../` to `../../`  
If shell cannot find test answer files or output files, please reset at this line.


