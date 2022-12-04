//Function to check if a singly linked list is palindrome
#define bool int
#include<stdio.h>
#include<stdlib.h>

/* Link list node */
struct node
{
    char data;
    struct node* next;
};

//first implementation
/*
bool isPalindrome(struct node **left, struct  node *right)
{
   //stop recursion here
  if (!right)
      return true;

   //If sub-list is not palindrome then no need to
     //  check for current left and right, return false
   bool isp = isPalindrome(left, right->next);
   if (isp == false)
      return false;

   //Check values at current left and right
  bool isp1 = (right->data == (*left)->data);

   //Move left to next node
  *left = (*left)->next; //save next pointer

   return isp1;
}*/

////Second implementation
static int check = 1 ;
struct node *left;
bool isPalindrome( struct  node *right)
{
    if ( !right)
    return true ;
    check++ ;
    isPalindrome(right->next);

    if ( left->data == right->data)
        {left = left->next ; check--;}
    else return false;
    if ( check==1)
        return true;

}



/* UTILITY FUNCTIONS */
/* Push a node to linked list. Note that this function
  changes the head */
void push(struct node** head_ref, char new_data)
{
    /* allocate node */
    struct node* new_node =
            (struct node*) malloc(sizeof(struct node));

    /* put in the data  */
    new_node->data  = new_data;

    /* link the old list off the new node */
    new_node->next = (*head_ref);

    /* move the head to pochar to the new node */
    (*head_ref)    = new_node;
}

/* Drier program to test above function*/
int main()
{
    /* Start with the empty list */
    struct node* head = NULL;

     push(&head, 'n');
     push(&head, 'i');
     push(&head, 't');
     push(&head, 'i');
     push(&head, 'n');

     /* r->a->d->a->r*/
     left = head ;
     if(isPalindrome( head) == 1)
       printf("Linked list is Palindrome");
     else
       printf("Linked list is not Palindrome");

     getchar();
     return 0;
}
