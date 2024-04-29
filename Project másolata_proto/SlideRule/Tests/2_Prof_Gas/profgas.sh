#/usr/bin/bash

java -jar ../SlideRule.jar ./test_profGas.txt ./result_profGas.txt

if grep -q "p1.isStunned : positive" ./result_profGas.txt; then
    echo -e "\e[32mTEST PROFGAS PASSED\e[0m"
else
    echo -e "\e[31mTEST PROFGAS FAILED\e[0m"
fi

