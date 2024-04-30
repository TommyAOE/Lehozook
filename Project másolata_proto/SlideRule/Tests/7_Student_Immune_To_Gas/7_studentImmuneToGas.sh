#/usr/bin/bash

java -jar ../SlideRule.jar ./test_studentImmuneToGas.txt ./result_studentImmuneToGas.txt

if grep -q "s1.isStunned : 0" ./result_studentImmuneToGas.txt; then
    echo -e "\e[32mTEST-7 STUDENT IMMUNE TO GAS PASSED\e[0m"
else
    echo -e "\e[31mTEST-7 STUDENT IMMUNE TO GAS FAILED\e[0m"
fi

