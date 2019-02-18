// Binary Tree Via Array (Linked Representation)
#include<iostream>
using namespace std;


class node
  {
  public:
    node *left ;
    node *right ;
    int data ;

  } ;

class Queue
{
            Queue *next ;
            node *info ;
    public:
           Queue ( )
           {
                 next = 0 ;

           }
           void EnQueue (  node *data );
           BTNode *DeQueue ();
           int IsEmptyQueue ( ) ;
           void DeleteQueue ( ) ;

}*front ,*rear;

int Queue::IsEmptyQueue ( )
{
    return ( front==0) ;
}

void Queue::EnQueue ( node *data )
{

       Queue *temp ;

      temp = new Queue ( );
      temp->info = data ;

      if ( rear == 0)
      rear = temp ;
      else
      rear->next = temp ;

      if ( !front)
      front = rear ;

}

BTNode * Queue::DeQueue ()
{

        if ( !IsEmptyQueue() )
         {
                node *data = front->info ;
                Queue *temp = front ;
                front = front->next ;
                delete temp ;
                return data ;
         }
         else{return (node *)0 ; }
}

void Queue::DeleteQueue ( )
{
    Queue *temp ;
    while ( !IsEmptyQueue() )
    {
        temp = front ;
        front = front->next;
        delete temp ;

    }

}

class btree
{
  node *root ;


  public:
        btree () ;
        void buildtree ();
        void Traversal ( ) ;
        void preorder (node *) ;
        void levelorder (node * , int) ;
        void boundaryTraversal (node *) ;
        int Height (node *) ;
        void printLeft (node *) ;
        void printRight (node *) ;
        void printLeaves(node *) ;
        void levelorderPrint (node *, int ) ;
        void buildTreeFromArray () ;
        void insertInBT (node *);
};

btree :: btree ()
{
  root = 0 ;

}

void btree :: buildTreeFromArray ()
{
    int n ;
    int i = 0 ;
    cout <<"\n Total number of nodes: "
    cin>>n ;

    int *array = new int [n] ;

    while ( i<n){
        cin >> array[i] ;
    }
  node *current;
  for (i = 0 ; i<n; i++)
  {
    current = new node () ;
    current->left = current->right = 0 ;
    current->data = array[i] ;

    insertInBT(current) ;

  }


}




BTNode * BinaryTree:: insertInBT(node *root , int data)
{
    Queue Q ;

    BTNode *newnode,*temp ;
    newnode = new BTNode();

    if (!newnode)
    {
        cout <<"\n insufficient Memory" ;
        return root;
    }
    newnode->info = data ;
    if ( !root ){//cout <<"h" ;
        root = newnode ;return root;}

    Q.EnQueue(root) ;
    while ( !Q.IsEmptyQueue())
    {
        temp = Q.DeQueue() ;
     //   cout <<"\t" <<temp->info <<"\n";
        if ( temp->left )
            Q.EnQueue(temp->left) ;
        else
        {
            temp->left = newnode;
           // Q.DeleteQueue() ;
            //cout <<"\n returning" ;
            return root;

        }
        if ( temp->right )
            Q.EnQueue(temp->right) ;
        else
        {
            temp->right = newnode ;
         //   cout <<"ehl" ;
            //Q.DeleteQueue() ;
         //   cout <<"\n returning" ;
            return root;

        }
    }
//cout <<"\nout" ;
    Q.DeleteQueue() ;
    return root ;
}

}

void  btree :: buildtree ()
{
    int i = 0 ;

    node *temp = 0 ;

    temp = new node () ;

    temp->data = 1;
    temp->left  = new node ();
    temp->left->data  = 2 ;
    temp->right = new node () ;
    temp->right->data = 3 ;

    root = temp ;

    temp= root->left ;
    temp->left = new node ();
    temp->left->data = 4 ;
    temp->right = new node ();
    temp->right ->data = 5 ;

    temp = root->right ;
    temp->left = new node ();
    temp->left->data = 6 ;
    temp->right = new node ();
    temp->right ->data = 7 ;


}


void btree :: Traversal ( )
{

    int n ;
    while ( 1){

        cout <<"\n Enter traversal Number" ;
        cout <<"\n 1. BFT " ;
        cout <<"\n 2. DFT ";
        cout <<"\n 3. Boundary Traversal ";
        cout <<"\n 4. Exit " ;
        cin >>n;
        if ( n == 4) break ;

        if (n == 1)
        {

               int height = Height (root) ;
                    levelorder(root, height) ;

        }
        if (n == 2)
        {
            preorder(root) ;
        }
        if (n == 3)
            {
                    boundaryTraversal(root) ;
            }

    }



}


void btree :: preorder(node *root)
{
    if (!root) return  ;

    cout <<root->data << " " ;
    preorder (root->left) ;
    preorder (root->right) ;

}


int btree :: Height ( node *root )
{

    if ( !root ) return 0 ;

    int left = Height (root->left ) ;
    int right = Height (root->right);

    return (( left > right ? left :right) + 1);
}

void btree::  levelorder(node *root, int height){
    int l = 0 ;

    for ( l = 0 ; l<height; l++)
        levelorderPrint (root, l) ;

}

void btree:: levelorderPrint (node *root, int l){

    if (!root)
    return  ;

    if ( l == 0)
        cout <<root->data << " " ;
    else {
        levelorderPrint ( root->left , l-1);
        levelorderPrint ( root ->right , l-1) ;
    }

}


void btree :: boundaryTraversal(node *root)
{
    if ( !root)
    return ;

    cout <<root->data << " ";

    printLeft ( root ->left ) ;

    printLeaves ( root->left ) ;

    printLeaves (root->right) ;

    printRight(root->right) ;


}

void btree :: printLeft ( node *root)
{

    if ( root)
    {
        if (root->left )
        {
          cout <<root->data << " " ;
          printLeft (root->left) ;
        }


    }
    else
     return  ;
}

void btree :: printLeaves ( node *root)
{

    if (root)
        {
            printLeaves ( root->left ) ;

            if ( !root->left && !root->right)
               cout << root->data << "  " ;

            printLeaves ( root->right ) ;
        }


}

void btree :: printRight (node *root)
{
     if ( root)
    {
        if (root->right)
        {
          printRight (root->right) ;
          cout <<root->data << " " ;

        }


    }
    else
     return  ;

}


int main ( )
{

    btree BT ;

    BT.buildtree () ;
    BT.Traversal () ;


}
