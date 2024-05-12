#/usr/bin/bash

java -jar ../SlideRule.jar ./test_studentNotImmuneToGas.txt ./result_studentNotImmuneToGas.txt

if grep -q "s1.isStunned : positive" ./result_studentNotImmuneToGas.txt; then
    echo -e "\e[32mTEST-9 STUDENT NOT IMMUNE TO GAS PASSED\e[0m"
else
    echo -e "\e[31mTEST-9 STUDENT NOT IMMUNE TO GAS FAILED\e[0m"
fi

