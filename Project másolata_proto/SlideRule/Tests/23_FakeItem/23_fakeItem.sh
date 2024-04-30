#/usr/bin/bash

java -jar ../SlideRule.jar ./test_fakeItem.txt ./result_fakeItem.txt

if grep -q "r1.students 0" ./result_fakeItem.txt && grep -q "r2.students 1" ./result_fakeItem.txt; then
    echo -e "\e[32mTEST-23 FAKE ITEM PASSED\e[0m"
else
    echo -e "\e[31mTEST-23 FAKE ITEM FAILED\e[0m"
fi

