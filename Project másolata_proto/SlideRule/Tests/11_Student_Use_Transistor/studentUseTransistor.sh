#/usr/bin/bash

java -jar ../SlideRule.jar ./test_studentUseTransistor.txt ./result_studentUseTransistor.txt

if grep -q "s1.location : r1" ./result_studentUseTransistor.txt && grep -q "r2.items 1" ./result_studentUseTransistor.txt; then
    echo -e "\e[32mTEST-11 STUDENT USE TRANSISTOR PASSED\e[0m"
else
    echo -e "\e[31mTEST-11 STUDENT USE TRANSISTOR FAILED\e[0m"
fi

