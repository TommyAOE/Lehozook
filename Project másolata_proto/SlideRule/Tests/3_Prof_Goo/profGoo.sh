#/usr/bin/bash

java -jar ../SlideRule.jar ./test_profGoo.txt ./result_profGoo.txt

if grep -q "r2.items 2" ./result_profGoo.txt; then
    echo -e "\e[32mTEST-3 PROF GOO PASSED\e[0m"
else
    echo -e "\e[31mTEST-3 PROF GOO FAILED\e[0m"
fi

