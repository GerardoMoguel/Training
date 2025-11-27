#include <iostream>
#include <string>
using namespace std;

bool isPalindrome(const string& s, int skip) {
    int l = 0, r = s.size() - 1;
    while (l < r) {
        if (l == skip) l++;
        if (r == skip) r--;
        if (s[l] != s[r]) return false;
        l++;
        r--;
    }
    return true;
}

int countRemovableIndices(const string& s) {
    int count = 0;
    for (int i = 0; i < s.size(); ++i) {
        if (isPalindrome(s, i)) count++;
    }
    return count;
}

int main() {
    string s = "a121a";
    cout << "Removable palindrome indices: " << countRemovableIndices(s) << endl;
    return 0;
}
