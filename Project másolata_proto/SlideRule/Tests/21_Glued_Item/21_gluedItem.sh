#/usr/bin/bash

java -jar ../SlideRule.jar ./test_gluedItem.txt ./result_gluedItem.txt

if grep -q "could not pick up" ./result_gluedItem.txt; then
    echo -e "\e[32mTEST-21 GLUED ITEM PASSED\e[0m"
else
    echo -e "\e[31mTEST-21 GLUED ITEM FAILED\e[0m"
fi

