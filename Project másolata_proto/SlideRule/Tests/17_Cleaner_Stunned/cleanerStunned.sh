#/usr/bin/bash

java -jar ../SlideRule.jar ./test_cleanerStunned.txt ./result_cleanerStunned.txt

if grep -q "r2.professors 1" ./result_cleanerStunned.txt && grep -q "r2.students 1" ./result_cleanerStunned.txt; then
    echo -e "\e[32mTEST-17 CLEANER STUNNED PASSED\e[0m"
else
    echo -e "\e[31mTEST-17 CLEANER STUNNED FAILED\e[0m"
fi

