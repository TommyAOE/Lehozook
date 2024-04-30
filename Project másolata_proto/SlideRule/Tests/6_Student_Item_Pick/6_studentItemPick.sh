#/usr/bin/bash

java -jar ../SlideRule.jar ./test_studentItemPick.txt ./result_studentItemPick.txt

if grep -q "r2.items 0" ./result_studentItemPick.txt && grep -q "s1.items : 1" ./result_studentItemPick.txt; then
    echo -e "\e[32mTEST-6 STUDENT ITEM PICK PASSED\e[0m"
else
    echo -e "\e[31mTEST-6 STUDENT ITEM PICK FAILED\e[0m"
fi

