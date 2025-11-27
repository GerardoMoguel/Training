#include <iostream>
#include <stack>
using namespace std;

 //code given by leetCode
 //this struct acts as chained 
struct ListNode {
    int val; //here we have the value of the node
    ListNode *next; //we get the pointer of the next node
    ListNode() : val(0), next(nullptr) {} //we assign the value as 0, and next as null
    ListNode(int x) : val(x), next(nullptr) {} //adds int as isolated value (or last value)
    ListNode(int x, ListNode *next) : val(x), next(next) {} // to add a "next" value
};


class AddTwoNumbers {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        //we are given two list nodes, representing a number.
        //structure of the input, if the number is 123, the order of the
        //list node will be (3) -> (2) -> (1)
        //representing the number 123.
        //We will be given 2 list nodes, we need to sum their representations.
        //and return them as another list node.
        stack<int>s1;
        stack<int>s2;
        ListNode * current = l1;
        while(current != nullptr){
            s1.push(current->val);
            current = current->next;
        }
        current = l2;
         while(current != nullptr){
            s2.push(current->val);
            current = current->next;
        }
        int first=0;
        while(!s1.empty()){
            first*=10;
            first+=s1.top();
            s1.pop();
        }

        int second=0;
        while(!s2.empty()){
            second*=10;
            second+=s2.top();
            s2.pop();
        }

        int result = first+second;
        ListNode res;
        cout<<result<<endl;
    }
};

int main(){
    ListNode a(2);
    ListNode b(1);
    a.next=&b;

    ListNode c(8);
    ListNode d(1);
    c.next=&d;

    AddTwoNumbers hi;
    hi.addTwoNumbers(&a,&c);
}
