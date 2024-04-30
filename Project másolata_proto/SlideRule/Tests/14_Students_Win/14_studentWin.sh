#/usr/bin/bash

java -jar ../SlideRule.jar ./test_studentWin.txt ./result_studentWin.txt

if grep -q "Students have won" ./result_studentWin.txt; then
    echo -e "\e[32mTEST-14 STUDENT WIN PASSED\e[0m"
else
    echo -e "\e[31mTEST-14 STUDENT WIN FAILED\e[0m"
fi

