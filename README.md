# [Insight Data Engineer Porject](https://github.com/InsightDataScience/find-political-donors)

# Approach
## Data structure
1.Used Map<String, List<Integer>> to keep record of contributions from a zip, used binary search to insert a single contribution to make the list of contribution ordered to get the running median effeciently.
2.Use TreeMap<String, TreeMap<String, List<Integer>>> to keep record of a recipient of contributions on a single date, used the same way as in zip to keep contributions in order, and used TreeMap to keep order of recipient and time.
## OOD
Used interface, abstract classes, etc. to implment algorithm in an object-oriented way to make project extendable, scalable and clean.
Different ways of handling a single record was created as an `Analyzer`, which has its own implementations of saving, writing to file , closing pipe methods. 
Data was read line by line in `DateReader`, and each line was applied different analyzers to do its own operations(filter, save, calculate, writeToFile) which is easy to extend more analyzers to scale the project.
Analyzer implmented `Filter` interface to make its sub-classes have own way to filter the record.
A single record was treated as a `Record` object to extract fields from records and have a more convinient way of passing and handling records.
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


