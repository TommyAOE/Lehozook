#/usr/bin/bash

java -jar ../SlideRule.jar ./test_WetRag.txt ./result_WetRag.txt

if grep -q "r2.students 2" ./result_WetRag.txt && grep -q "p1.isStunned : positive" ./result_WetRag.txt && grep -q "p2.isStunned : positive" ./result_WetRag.txt; then
    echo -e "\e[32mTEST-13 STUDENT USE WETRAG PASSED\e[0m"
else
    echo -e "\e[31mTEST-13 STUDENT USE WETRAG FAILED\e[0m"
fi

